(* === split === *)
let _ =
  assert (split [1; 2; 3; 4] = ([1; 2], [3; 4]));
  assert (split [1; 2; 3; 4; 5] = ([1; 2; 3], [4; 5]))

(* === filter_map === *)
let _ =
  assert (filter_map (fun x -> if x mod 2 = 0 then Some (x * 2) else None) [1; 2; 3; 4] = [4; 8]);
  assert (filter_map (fun x -> if x > 0 then Some (string_of_int x) else None) [-2; 3; -1; 4] = ["3"; "4"])

(* === tree_map === *)
let _ =
  assert (tree_map (fun x -> x + 1) (Node (1, Leaf, Node (2, Leaf, Leaf))) = Node (2, Leaf, Node (3, Leaf, Leaf)));
  assert (tree_map string_of_int (Node (1, Node (2, Leaf, Leaf), Leaf)) = Node ("1", Node ("2", Leaf, Leaf), Leaf))

(* === flatten and flatten_tail === *)
let _ =
  assert (flatten [[1; 2]; [3]; []; [4; 5]] = [1; 2; 3; 4; 5]);
  assert (flatten [[]; [42]] = [42]);
  assert (flatten_tail [[1]; [2; 3]; [4]] = [1; 2; 3; 4]);
  assert (flatten_tail [[5; 6]; []] = [5; 6])

(* === safe_div and safe_div_try === *)
let _ =
  assert (safe_div_option 10 2 = Some 5);
  assert (safe_div_option 10 0 = None)

(* === shape and area === *)
let _ =
  assert (int_of_float (area (Circle 1.0)) = 3);
  assert (area (Rectangle (3.0, 4.0)) = 12.0)

(* === find_index === *)
let _ =
  assert (find_index (fun x -> x = 3) [1; 2; 3; 4] = 2);
  try let _ = find_index (fun x -> x > 10) [1; 2; 3] in assert false
  with NotFound (3, 3) -> ()

(* === parse_int_list === *)
let _ =
  assert (parse_int_list "1,2,3" = [1; 2; 3]);
  try let _ = parse_int_list "1,a,3" in assert false
  with InvalidInput _ -> ()
