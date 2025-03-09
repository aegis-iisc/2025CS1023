open Stdio

let hello_world name = 
    printf "Hello %s" (name)


let _ = 
  let line = In_channel.input_line In_channel.stdin in
  match line with 
  | None -> hello_world "Anonymous"
  | Some x -> hello_world x 


 
