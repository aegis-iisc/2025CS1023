import os

def combine_ml_files(source_dir='functions', output_file='answers.ml'):
    with open(output_file, 'w') as outfile:
        for filename in sorted(os.listdir(source_dir)):
            if filename.endswith('.ml'):
                file_path = os.path.join(source_dir, filename)
                with open(file_path, 'r') as infile:
                    contents = infile.read()
                    outfile.write(f"(* File: {filename} *)\n")
                    outfile.write(contents)
                    outfile.write("\n\n")
    print(f"✅ Combined all .ml files into {output_file}")

if __name__ == '__main__':
    combine_ml_files()
