// Benchmark "xor4.blif" written by ABC on Thu Mar 18 05:26:12 2021

module \xor4.blif  ( 
    A, B, C, D,
    Y  );
  input  A, B, C, D;
  output Y;
  wire new_n6_, new_n7_, new_n8_, new_n9_, new_n10_, new_n11_;
  INV    g0(.A(C), .Y(new_n6_));
  XOR2   g1(.A(D), .B(new_n6_), .Y(new_n7_));
  XOR2   g2(.A(B), .B(A), .Y(new_n8_));
  XOR2   g3(.A(D), .B(C), .Y(new_n9_));
  INV    g4(.A(A), .Y(new_n10_));
  XOR2   g5(.A(B), .B(new_n10_), .Y(new_n11_));
  OAI22  g6(.A0(new_n11_), .A1(new_n9_), .B0(new_n8_), .B1(new_n7_), .Y(Y));
endmodule


