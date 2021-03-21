// Benchmark "xor3.blif" written by ABC on Thu Mar 18 03:51:25 2021

module \xor3.blif  ( 
    A, B, C,
    Y  );
  input  A, B, C;
  output Y;
  wire new_n5_, new_n6_, new_n7_, new_n8_, new_n9_, new_n10_, new_n11_,
    new_n12_;
  INV   g0(.A(B), .Y(new_n5_));
  INV   g1(.A(C), .Y(new_n6_));
  NOR2  g2(.A(new_n6_), .B(new_n5_), .Y(new_n7_));
  NOR2  g3(.A(C), .B(B), .Y(new_n8_));
  OAI21 g4(.A0(new_n8_), .A1(new_n7_), .B0(A), .Y(new_n9_));
  NOR2  g5(.A(new_n6_), .B(B), .Y(new_n10_));
  NOR2  g6(.A(C), .B(new_n5_), .Y(new_n11_));
  NOR2  g7(.A(new_n11_), .B(new_n10_), .Y(new_n12_));
  OAI21 g8(.A0(new_n12_), .A1(A), .B0(new_n9_), .Y(Y));
endmodule


