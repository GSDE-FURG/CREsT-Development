// Benchmark "xor5.blif" written by ABC on Thu Mar 18 04:45:36 2021

module \xor5.blif  ( 
    A, B, C, D, E,
    Y  );
  input  A, B, C, D, E;
  output Y;
  wire new_n7_, new_n8_, new_n9_, new_n10_, new_n11_, new_n12_, new_n13_,
    new_n14_, new_n15_, new_n16_, new_n17_, new_n18_, new_n19_, new_n20_,
    new_n21_, new_n22_, new_n23_, new_n24_, new_n25_, new_n26_, new_n27_,
    new_n28_, new_n29_, new_n30_, new_n31_;
  NAND2 g00(.A(E), .B(D), .Y(new_n7_));
  INV   g01(.A(D), .Y(new_n8_));
  INV   g02(.A(E), .Y(new_n9_));
  NAND2 g03(.A(new_n9_), .B(new_n8_), .Y(new_n10_));
  NAND2 g04(.A(new_n10_), .B(new_n7_), .Y(new_n11_));
  NAND2 g05(.A(C), .B(B), .Y(new_n12_));
  INV   g06(.A(B), .Y(new_n13_));
  INV   g07(.A(C), .Y(new_n14_));
  NAND2 g08(.A(new_n14_), .B(new_n13_), .Y(new_n15_));
  NAND2 g09(.A(new_n15_), .B(new_n12_), .Y(new_n16_));
  NAND2 g10(.A(new_n16_), .B(A), .Y(new_n17_));
  INV   g11(.A(A), .Y(new_n18_));
  NAND2 g12(.A(C), .B(new_n13_), .Y(new_n19_));
  NAND2 g13(.A(new_n14_), .B(B), .Y(new_n20_));
  NAND2 g14(.A(new_n20_), .B(new_n19_), .Y(new_n21_));
  NAND2 g15(.A(new_n21_), .B(new_n18_), .Y(new_n22_));
  NAND2 g16(.A(new_n22_), .B(new_n17_), .Y(new_n23_));
  NAND2 g17(.A(new_n23_), .B(new_n11_), .Y(new_n24_));
  NAND2 g18(.A(E), .B(new_n8_), .Y(new_n25_));
  NAND2 g19(.A(new_n9_), .B(D), .Y(new_n26_));
  NAND2 g20(.A(new_n26_), .B(new_n25_), .Y(new_n27_));
  NAND2 g21(.A(new_n21_), .B(A), .Y(new_n28_));
  NAND2 g22(.A(new_n16_), .B(new_n18_), .Y(new_n29_));
  NAND2 g23(.A(new_n29_), .B(new_n28_), .Y(new_n30_));
  NAND2 g24(.A(new_n30_), .B(new_n27_), .Y(new_n31_));
  NAND2 g25(.A(new_n31_), .B(new_n24_), .Y(Y));
endmodule


