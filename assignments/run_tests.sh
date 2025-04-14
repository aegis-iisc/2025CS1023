#!/bin/bash

# Path to the OCaml file that contains both the functions and the tests
INPUT_FILE="answers.ml"


# Usage function
usage() {
    echo "Usage: $0 <input_file>"
    exit 1
}

# Check arguments
if [ $# -ne 1 ]; then
    usage
fi

INPUT_FILE="$1"


# Check if the input file exists
if [ ! -f "$INPUT_FILE" ]; then
    echo "❌ Error: File '$INPUT_FILE' not found."
    exit 1
fi

# Compile the OCaml file
echo "🔨 Compiling '$INPUT_FILE'..."
ocamlc -o answers.byte "$INPUT_FILE"

# Check if the compilation was successful
if [ $? -ne 0 ]; then
    echo "❌ Compilation failed."
    exit 1
fi

# Run the compiled program
echo "🚀 Running tests..."
./answers.byte

# Clean up the compiled bytecode file
rm -f answers.byte

echo "✅ Tests executed successfully."
