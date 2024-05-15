#!/bin/bash

# Loop through each directory
for dir in */; do
  echo "Processing directory: $dir"

  # Find all .m4s files in the current directory
  m4s_files=($(find "$dir" -maxdepth 1 -type f -iname "*.m4s"))

  if [ ${#m4s_files[@]} -ne 2 ]; then
    echo "Skipping directory $dir: expected 2 .m4s files but found ${#m4s_files[@]}"
    continue
  fi

  # Identify which file is video and which is audio
  for file in "${m4s_files[@]}"; do
    stream_info=$(ffmpeg -i "$file" 2>&1)
    if echo "$stream_info" | grep -q 'Video:'; then
      video_file="$file"
    elif echo "$stream_info" | grep -q 'Audio:'; then
      audio_file="$file"
    fi
  done

  if [ -z "$video_file" ] || [ -z "$audio_file" ]; then
    echo "Could not determine video or audio file in directory $dir"
    continue
  fi

  # Output file name
  dir_name=$(basename "$dir")
  output_file="${dir_name}.mp4"

  # Merge video and audio
  ffmpeg -i "$video_file" -i "$audio_file" -c copy "$output_file"

  echo "Created $output_file"
done

echo "All done!"
