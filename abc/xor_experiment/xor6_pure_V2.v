module xor6v2  ( 
    A, B, C, D, E, F,
    Y  );
  input  A, B, C, D, E, F;
  output Y;
  wire w1, w2, w3, w4;
  XOR2 g0(.A(A), .B(B), .Y(w1));
  XOR2 g1(.A(C), .B(D), .Y(w2));
  XOR2 g2(.A(w1), .B(w2), .Y(w3));
  XOR2 g3(.A(E), .B(F), .Y(w4));
  XOR2 g4(.A(w3), .B(w4), .Y(Y));
endmodule

