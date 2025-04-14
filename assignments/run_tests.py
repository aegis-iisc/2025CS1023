import subprocess
import os

def run_ocaml_tests(answers_file='answers.ml', tests_file='tests.ml', output_file='test_exec'):
    try:
        # Step 1: Compile answers.ml and tests.ml
        compile_command = ['ocamlc', '-o', output_file, answers_file, tests_file]
        print(f"Compiling with command: {' '.join(compile_command)}")
        compile_result = subprocess.run(compile_command, capture_output=True, text=True)

        if compile_result.returncode != 0:
            print("❌ Compilation failed:")
            print(compile_result.stderr)
            return

        print("✅ Compilation succeeded.")

        # Step 2: Run the compiled binary
        print("🔍 Running test binary...")
        run_result = subprocess.run([f'./{output_file}'], capture_output=True, text=True)

        if run_result.returncode == 0:
            print("✅ All tests passed.")
        else:
            print("⚠️ Tests ran with errors:")
            print(run_result.stderr)

        if run_result.stdout.strip():
            print("📤 Output:")
            print(run_result.stdout)

    finally:
        # Cleanup
        if os.path.exists(output_file):
            os.remove(output_file)
        for ext in ['.cmi', '.cmo']:
            for file in [answers_file, tests_file]:
                compiled = file.replace('.ml', ext)
                if os.path.exists(compiled):
                    os.remove(compiled)

if __name__ == '__main__':
    run_ocaml_tests()
