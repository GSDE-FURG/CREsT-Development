// Benchmark "xor3.blif" written by ABC on Thu Mar 18 05:23:24 2021

module \xor3.blif  ( 
    A, B, C,
    Y  );
  input  A, B, C;
  output Y;
  wire new_n5_, new_n6_, new_n7_, new_n8_;
  INV    g0(.A(A), .Y(new_n5_));
  XOR2   g1(.A(C), .B(B), .Y(new_n6_));
  XOR2   g2(.A(C), .B(B), .Y(new_n7_));
  NAND2  g3(.A(new_n7_), .B(new_n5_), .Y(new_n8_));
  OAI21  g4(.A0(new_n6_), .A1(new_n5_), .B0(new_n8_), .Y(Y));
endmodule


