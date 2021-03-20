// Benchmark "xor4.blif" written by ABC on Thu Mar 18 04:34:19 2021

module \xor4.blif  ( 
    A, B, C, D,
    Y  );
  input  A, B, C, D;
  output Y;
  wire new_n6_, new_n7_, new_n8_, new_n9_, new_n10_, new_n11_, new_n12_,
    new_n13_, new_n14_, new_n15_, new_n16_, new_n17_, new_n18_, new_n19_;
  INV   g00(.A(D), .Y(new_n6_));
  NOR2  g01(.A(new_n6_), .B(C), .Y(new_n7_));
  INV   g02(.A(C), .Y(new_n8_));
  NOR2  g03(.A(D), .B(new_n8_), .Y(new_n9_));
  INV   g04(.A(A), .Y(new_n10_));
  INV   g05(.A(B), .Y(new_n11_));
  NOR2  g06(.A(new_n11_), .B(new_n10_), .Y(new_n12_));
  NOR2  g07(.A(B), .B(A), .Y(new_n13_));
  OAI22 g08(.A0(new_n13_), .A1(new_n12_), .B0(new_n9_), .B1(new_n7_), .Y(new_n14_));
  NOR2  g09(.A(new_n6_), .B(new_n8_), .Y(new_n15_));
  NOR2  g10(.A(D), .B(C), .Y(new_n16_));
  NOR2  g11(.A(new_n11_), .B(A), .Y(new_n17_));
  NOR2  g12(.A(B), .B(new_n10_), .Y(new_n18_));
  OAI22 g13(.A0(new_n18_), .A1(new_n17_), .B0(new_n16_), .B1(new_n15_), .Y(new_n19_));
  NAND2 g14(.A(new_n19_), .B(new_n14_), .Y(Y));
endmodule


