// Benchmark "xor5.blif" written by ABC on Thu Mar 18 04:48:20 2021

module \xor5.blif  ( 
    A, B, C, D, E,
    Y  );
  input  A, B, C, D, E;
  output Y;
  wire new_n7_, new_n8_, new_n9_, new_n10_, new_n11_, new_n12_, new_n13_,
    new_n14_, new_n15_, new_n16_, new_n17_, new_n18_, new_n19_, new_n20_,
    new_n21_, new_n22_, new_n23_, new_n24_, new_n25_;
  INV   g00(.A(D), .Y(new_n7_));
  INV   g01(.A(E), .Y(new_n8_));
  NOR2  g02(.A(new_n8_), .B(new_n7_), .Y(new_n9_));
  NOR2  g03(.A(E), .B(D), .Y(new_n10_));
  INV   g04(.A(A), .Y(new_n11_));
  NAND2 g05(.A(C), .B(B), .Y(new_n12_));
  INV   g06(.A(B), .Y(new_n13_));
  INV   g07(.A(C), .Y(new_n14_));
  NAND2 g08(.A(new_n14_), .B(new_n13_), .Y(new_n15_));
  AOI21 g09(.A0(new_n15_), .A1(new_n12_), .B0(new_n11_), .Y(new_n16_));
  NAND2 g10(.A(C), .B(new_n13_), .Y(new_n17_));
  NAND2 g11(.A(new_n14_), .B(B), .Y(new_n18_));
  AOI21 g12(.A0(new_n18_), .A1(new_n17_), .B0(A), .Y(new_n19_));
  OAI22 g13(.A0(new_n19_), .A1(new_n16_), .B0(new_n10_), .B1(new_n9_), .Y(new_n20_));
  NOR2  g14(.A(new_n8_), .B(D), .Y(new_n21_));
  NOR2  g15(.A(E), .B(new_n7_), .Y(new_n22_));
  AOI21 g16(.A0(new_n18_), .A1(new_n17_), .B0(new_n11_), .Y(new_n23_));
  AOI21 g17(.A0(new_n15_), .A1(new_n12_), .B0(A), .Y(new_n24_));
  OAI22 g18(.A0(new_n24_), .A1(new_n23_), .B0(new_n22_), .B1(new_n21_), .Y(new_n25_));
  NAND2 g19(.A(new_n25_), .B(new_n20_), .Y(Y));
endmodule


