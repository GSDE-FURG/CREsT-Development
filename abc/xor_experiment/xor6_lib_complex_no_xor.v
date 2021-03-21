// Benchmark "xor6.blif" written by ABC on Thu Mar 18 05:08:12 2021

module \xor6.blif  ( 
    A, B, C, D, E, F,
    Y  );
  input  A, B, C, D, E, F;
  output Y;
  wire new_n8_, new_n9_, new_n10_, new_n11_, new_n12_, new_n13_, new_n14_,
    new_n15_, new_n16_, new_n17_, new_n18_, new_n19_, new_n20_, new_n21_,
    new_n22_, new_n23_, new_n24_, new_n25_, new_n26_, new_n27_, new_n28_,
    new_n29_, new_n30_, new_n31_;
  INV   g00(.A(F), .Y(new_n8_));
  NOR2  g01(.A(new_n8_), .B(E), .Y(new_n9_));
  INV   g02(.A(E), .Y(new_n10_));
  NOR2  g03(.A(F), .B(new_n10_), .Y(new_n11_));
  NAND2 g04(.A(D), .B(C), .Y(new_n12_));
  INV   g05(.A(C), .Y(new_n13_));
  INV   g06(.A(D), .Y(new_n14_));
  NAND2 g07(.A(new_n14_), .B(new_n13_), .Y(new_n15_));
  NAND2 g08(.A(B), .B(A), .Y(new_n16_));
  INV   g09(.A(A), .Y(new_n17_));
  INV   g10(.A(B), .Y(new_n18_));
  NAND2 g11(.A(new_n18_), .B(new_n17_), .Y(new_n19_));
  AOI22 g12(.A0(new_n19_), .A1(new_n16_), .B0(new_n15_), .B1(new_n12_), .Y(new_n20_));
  NAND2 g13(.A(D), .B(new_n13_), .Y(new_n21_));
  NAND2 g14(.A(new_n14_), .B(C), .Y(new_n22_));
  NAND2 g15(.A(B), .B(new_n17_), .Y(new_n23_));
  NAND2 g16(.A(new_n18_), .B(A), .Y(new_n24_));
  AOI22 g17(.A0(new_n24_), .A1(new_n23_), .B0(new_n22_), .B1(new_n21_), .Y(new_n25_));
  OAI22 g18(.A0(new_n25_), .A1(new_n20_), .B0(new_n11_), .B1(new_n9_), .Y(new_n26_));
  NOR2  g19(.A(new_n8_), .B(new_n10_), .Y(new_n27_));
  NOR2  g20(.A(F), .B(E), .Y(new_n28_));
  AOI22 g21(.A0(new_n22_), .A1(new_n21_), .B0(new_n19_), .B1(new_n16_), .Y(new_n29_));
  AOI22 g22(.A0(new_n24_), .A1(new_n23_), .B0(new_n15_), .B1(new_n12_), .Y(new_n30_));
  OAI22 g23(.A0(new_n30_), .A1(new_n29_), .B0(new_n28_), .B1(new_n27_), .Y(new_n31_));
  NAND2 g24(.A(new_n31_), .B(new_n26_), .Y(Y));
endmodule


