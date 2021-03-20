// Benchmark "xor6.blif" written by ABC on Thu Mar 18 05:05:18 2021

module \xor6.blif  ( 
    A, B, C, D, E, F,
    Y  );
  input  A, B, C, D, E, F;
  output Y;
  wire new_n8_, new_n9_, new_n10_, new_n11_, new_n12_, new_n13_, new_n14_,
    new_n15_, new_n16_, new_n17_, new_n18_, new_n19_, new_n20_, new_n21_,
    new_n22_, new_n23_, new_n24_, new_n25_, new_n26_, new_n27_, new_n28_,
    new_n29_, new_n30_, new_n31_, new_n32_, new_n33_, new_n34_, new_n35_,
    new_n36_, new_n37_, new_n38_, new_n39_;
  INV   g00(.A(E), .Y(new_n8_));
  NAND2 g01(.A(F), .B(new_n8_), .Y(new_n9_));
  INV   g02(.A(F), .Y(new_n10_));
  NAND2 g03(.A(new_n10_), .B(E), .Y(new_n11_));
  NAND2 g04(.A(new_n11_), .B(new_n9_), .Y(new_n12_));
  NAND2 g05(.A(D), .B(C), .Y(new_n13_));
  INV   g06(.A(C), .Y(new_n14_));
  INV   g07(.A(D), .Y(new_n15_));
  NAND2 g08(.A(new_n15_), .B(new_n14_), .Y(new_n16_));
  NAND2 g09(.A(new_n16_), .B(new_n13_), .Y(new_n17_));
  NAND2 g10(.A(B), .B(A), .Y(new_n18_));
  INV   g11(.A(A), .Y(new_n19_));
  INV   g12(.A(B), .Y(new_n20_));
  NAND2 g13(.A(new_n20_), .B(new_n19_), .Y(new_n21_));
  NAND2 g14(.A(new_n21_), .B(new_n18_), .Y(new_n22_));
  NAND2 g15(.A(new_n22_), .B(new_n17_), .Y(new_n23_));
  NAND2 g16(.A(D), .B(new_n14_), .Y(new_n24_));
  NAND2 g17(.A(new_n15_), .B(C), .Y(new_n25_));
  NAND2 g18(.A(new_n25_), .B(new_n24_), .Y(new_n26_));
  NAND2 g19(.A(B), .B(new_n19_), .Y(new_n27_));
  NAND2 g20(.A(new_n20_), .B(A), .Y(new_n28_));
  NAND2 g21(.A(new_n28_), .B(new_n27_), .Y(new_n29_));
  NAND2 g22(.A(new_n29_), .B(new_n26_), .Y(new_n30_));
  NAND2 g23(.A(new_n30_), .B(new_n23_), .Y(new_n31_));
  NAND2 g24(.A(new_n31_), .B(new_n12_), .Y(new_n32_));
  NAND2 g25(.A(F), .B(E), .Y(new_n33_));
  NAND2 g26(.A(new_n10_), .B(new_n8_), .Y(new_n34_));
  NAND2 g27(.A(new_n34_), .B(new_n33_), .Y(new_n35_));
  NAND2 g28(.A(new_n26_), .B(new_n22_), .Y(new_n36_));
  NAND2 g29(.A(new_n29_), .B(new_n17_), .Y(new_n37_));
  NAND2 g30(.A(new_n37_), .B(new_n36_), .Y(new_n38_));
  NAND2 g31(.A(new_n38_), .B(new_n35_), .Y(new_n39_));
  NAND2 g32(.A(new_n39_), .B(new_n32_), .Y(Y));
endmodule


