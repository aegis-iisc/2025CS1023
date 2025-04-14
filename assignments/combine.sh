#!/bin/bash

# Usage function
usage() {
    echo "Usage: $0 <input_directory> <output_file> <test_file>"
    exit 1
}

# Check arguments
if [ $# -ne 3 ]; then
    usage
fi

INPUT_DIR="$1"
OUTPUT_FILE="$2"
TEST_FILE="$3"

# Check if input directory exists
if [ ! -d "$INPUT_DIR" ]; then
    echo "❌ Error: Directory '$INPUT_DIR' does not exist."
    exit 1
fi

# Clear or create the output file
> "$OUTPUT_FILE"

# Combine all .ml files from the input directory
for file in "$INPUT_DIR"/*.ml; do
    echo "(* File: $(basename "$file") *)" >> "$OUTPUT_FILE"
    cat "$file" >> "$OUTPUT_FILE"
    echo -e "\n" >> "$OUTPUT_FILE"
done
echo "$TEST_FILE"
cat "$TEST_FILE" >> "$OUTPUT_FILE"

echo "✅ Combined all .ml files from '$INPUT_DIR' into '$OUTPUT_FILE'"
