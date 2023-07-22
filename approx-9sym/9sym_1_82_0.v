// Benchmark "9sym/9sym_1_82_0_esp" written by ABC on Wed Mar  8 02:35:24 2023

module \9sym/9sym_1_82_0_esp  ( 
    i0, i1, i2, i3, i4, i5, i6, i7, i8,
    zori0  );
  input  i0, i1, i2, i3, i4, i5, i6, i7, i8;
  output zori0;
  wire new_n11_, new_n12_, new_n13_, new_n14_, new_n15_, new_n16_, new_n17_,
    new_n18_, new_n19_, new_n20_, new_n21_, new_n22_, new_n23_, new_n24_,
    new_n25_, new_n26_, new_n27_, new_n28_, new_n29_, new_n30_, new_n31_,
    new_n32_, new_n33_, new_n34_, new_n35_, new_n36_, new_n37_, new_n38_,
    new_n39_, new_n40_, new_n41_, new_n42_, new_n43_, new_n44_, new_n45_,
    new_n46_, new_n47_, new_n48_, new_n49_, new_n50_, new_n51_, new_n52_,
    new_n53_, new_n54_;
  INV        g00(.A(i4), .Y(new_n11_));
  INV        g01(.A(i5), .Y(new_n12_));
  INV        g02(.A(i8), .Y(new_n13_));
  OAI210     g03(.A0(i3), .A1(i1), .B0(i2), .Y(new_n14_));
  AN2        g04(.A(i3), .B(i2), .Y(new_n15_));
  AOI220     g05(.A0(new_n15_), .A1(new_n13_), .B0(new_n14_), .B1(i7), .Y(new_n16_));
  NO2        g06(.A(new_n16_), .B(new_n12_), .Y(new_n17_));
  INV        g07(.A(i0), .Y(new_n18_));
  NAi21      g08(.An(i8), .B(i6), .Y(new_n19_));
  INV        g09(.A(i2), .Y(new_n20_));
  AOI210     g10(.A0(new_n13_), .A1(i5), .B0(new_n20_), .Y(new_n21_));
  OAI210     g11(.A0(i6), .A1(i2), .B0(i1), .Y(new_n22_));
  OAI220     g12(.A0(new_n22_), .A1(new_n21_), .B0(new_n19_), .B1(new_n18_), .Y(new_n23_));
  OAI210     g13(.A0(new_n23_), .A1(new_n17_), .B0(new_n11_), .Y(new_n24_));
  INV        g14(.A(i6), .Y(new_n25_));
  NAi21      g15(.An(i5), .B(i7), .Y(new_n26_));
  OR2        g16(.A(new_n26_), .B(new_n25_), .Y(new_n27_));
  NOi21      g17(.An(i4), .B(i6), .Y(new_n28_));
  AOI210     g18(.A0(i8), .A1(new_n11_), .B0(new_n28_), .Y(new_n29_));
  AOI210     g19(.A0(new_n29_), .A1(new_n27_), .B0(new_n20_), .Y(new_n30_));
  INV        g20(.A(i3), .Y(new_n31_));
  AOI210     g21(.A0(i8), .A1(i1), .B0(i2), .Y(new_n32_));
  NO2        g22(.A(new_n32_), .B(new_n12_), .Y(new_n33_));
  AOI210     g23(.A0(i8), .A1(i5), .B0(i1), .Y(new_n34_));
  NO3        g24(.A(new_n34_), .B(new_n33_), .C(new_n31_), .Y(new_n35_));
  OAI210     g25(.A0(new_n35_), .A1(new_n30_), .B0(new_n18_), .Y(new_n36_));
  XN2        g26(.A(i7), .B(i6), .Y(new_n37_));
  OAI220     g27(.A0(new_n37_), .A1(i3), .B0(new_n19_), .B1(i5), .Y(new_n38_));
  NA2        g28(.A(new_n38_), .B(i4), .Y(new_n39_));
  NAi21      g29(.An(i7), .B(i6), .Y(new_n40_));
  NO3        g30(.A(new_n40_), .B(new_n31_), .C(i1), .Y(new_n41_));
  NO3        g31(.A(i6), .B(i3), .C(new_n18_), .Y(new_n42_));
  OAI210     g32(.A0(new_n42_), .A1(new_n41_), .B0(i2), .Y(new_n43_));
  OAI210     g33(.A0(i7), .A1(i2), .B0(i5), .Y(new_n44_));
  NA4        g34(.A(new_n44_), .B(new_n25_), .C(i3), .D(i0), .Y(new_n45_));
  INV        g35(.A(i1), .Y(new_n46_));
  NO2        g36(.A(i8), .B(new_n12_), .Y(new_n47_));
  INV        g37(.A(i7), .Y(new_n48_));
  NAi21      g38(.An(i2), .B(i3), .Y(new_n49_));
  OAI220     g39(.A0(new_n49_), .A1(new_n40_), .B0(new_n48_), .B1(i1), .Y(new_n50_));
  NAi31      g40(.An(i7), .B(i8), .C(i0), .Y(new_n51_));
  OAI210     g41(.A0(new_n49_), .A1(new_n26_), .B0(new_n51_), .Y(new_n52_));
  AOI220     g42(.A0(new_n52_), .A1(new_n46_), .B0(new_n50_), .B1(new_n47_), .Y(new_n53_));
  AN4        g43(.A(new_n53_), .B(new_n45_), .C(new_n43_), .D(new_n39_), .Y(new_n54_));
  NA3        g44(.A(new_n54_), .B(new_n36_), .C(new_n24_), .Y(zori0));
endmodule


