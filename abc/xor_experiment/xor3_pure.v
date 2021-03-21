module xor3a  ( 
    A, B, C,
    Y  );
  input  A, B, C;
  output Y;
  wire w1;
  XOR2 g0(.A(A), .B(B), .Y(w1));
  XOR2 g1(.A(w1), .B(C), .Y(Y));
endmodule

