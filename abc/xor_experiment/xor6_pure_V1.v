module xor6v1  ( 
    A, B, C, D, E, F,
    Y  );
  input  A, B, C, D, E, F;
  output Y;
  wire w1, w2, w3, w4;
  XOR2 g0(.A(A), .B(B), .Y(w1));
  XOR2 g1(.A(w1), .B(C), .Y(w2));
  XOR2 g2(.A(w2), .B(D), .Y(w3));
  XOR2 g3(.A(w3), .B(E), .Y(w4));
  XOR2 g4(.A(w4), .B(F), .Y(Y));
endmodule

