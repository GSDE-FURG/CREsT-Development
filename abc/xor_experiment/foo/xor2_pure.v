module xor2a  ( 
    A, B,
    Y  );
  input  A, B;
  output Y;
  XOR2 g0(.A(A), .B(B), .Y(Y));
endmodule
