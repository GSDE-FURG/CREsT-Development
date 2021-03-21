module xor4v2  ( 
    A, B, C, D,
    Y  );
  input  A, B, C, D;
  output Y;
  wire w1, w2;
  XOR2 g0(.A(A), .B(B), .Y(w1));
  XOR2 g1(.A(C), .B(D), .Y(w2));
  XOR2 g2(.A(w1), .B(w2), .Y(Y));
endmodule

