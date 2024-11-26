// Benchmark "9sym/9sym_1_81_0_esp" written by ABC on Wed Mar  8 02:35:12 2023

module \9sym/9sym_1_81_0_esp  ( 
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
    new_n53_, new_n54_, new_n55_, new_n56_;
  INV        g00(.A(i8), .Y(new_n11_));
  NA2        g01(.A(new_n11_), .B(i5), .Y(new_n12_));
  INV        g02(.A(i4), .Y(new_n13_));
  NA2        g03(.A(new_n13_), .B(i2), .Y(new_n14_));
  INV        g04(.A(i7), .Y(new_n15_));
  NOi21      g05(.An(i6), .B(i2), .Y(new_n16_));
  NA2        g06(.A(new_n16_), .B(new_n15_), .Y(new_n17_));
  AO210      g07(.A0(new_n17_), .A1(new_n14_), .B0(new_n12_), .Y(new_n18_));
  INV        g08(.A(i1), .Y(new_n19_));
  INV        g09(.A(i5), .Y(new_n20_));
  OAI210     g10(.A0(new_n11_), .A1(new_n20_), .B0(new_n19_), .Y(new_n21_));
  NA3        g11(.A(i8), .B(i5), .C(i1), .Y(new_n22_));
  AOI210     g12(.A0(i5), .A1(i2), .B0(i0), .Y(new_n23_));
  NA3        g13(.A(new_n23_), .B(new_n22_), .C(new_n21_), .Y(new_n24_));
  INV        g14(.A(i6), .Y(new_n25_));
  INV        g15(.A(i2), .Y(new_n26_));
  NA2        g16(.A(new_n15_), .B(new_n26_), .Y(new_n27_));
  NA2        g17(.A(new_n27_), .B(i5), .Y(new_n28_));
  NA3        g18(.A(new_n28_), .B(new_n25_), .C(i0), .Y(new_n29_));
  OAI220     g19(.A0(i7), .A1(new_n25_), .B0(i5), .B1(i2), .Y(new_n30_));
  NA3        g20(.A(new_n30_), .B(new_n27_), .C(new_n19_), .Y(new_n31_));
  NA4        g21(.A(new_n31_), .B(new_n29_), .C(new_n24_), .D(new_n18_), .Y(new_n32_));
  INV        g22(.A(i3), .Y(new_n33_));
  NA3        g23(.A(new_n16_), .B(i8), .C(new_n33_), .Y(new_n34_));
  AOI210     g24(.A0(new_n34_), .A1(new_n12_), .B0(i1), .Y(new_n35_));
  NO3        g25(.A(i6), .B(new_n13_), .C(i3), .Y(new_n36_));
  OAI210     g26(.A0(new_n36_), .A1(new_n35_), .B0(i7), .Y(new_n37_));
  NA2        g27(.A(i6), .B(new_n26_), .Y(new_n38_));
  NA3        g28(.A(new_n11_), .B(i5), .C(i2), .Y(new_n39_));
  AOI210     g29(.A0(new_n39_), .A1(new_n38_), .B0(new_n19_), .Y(new_n40_));
  OAI210     g30(.A0(i3), .A1(i1), .B0(i2), .Y(new_n41_));
  NA3        g31(.A(new_n41_), .B(i7), .C(i5), .Y(new_n42_));
  NAi31      g32(.An(i0), .B(i8), .C(i2), .Y(new_n43_));
  NA3        g33(.A(new_n11_), .B(i6), .C(i0), .Y(new_n44_));
  NA3        g34(.A(new_n44_), .B(new_n43_), .C(new_n42_), .Y(new_n45_));
  OAI210     g35(.A0(new_n45_), .A1(new_n40_), .B0(new_n13_), .Y(new_n46_));
  NO3        g36(.A(i6), .B(i3), .C(new_n26_), .Y(new_n47_));
  NO3        g37(.A(new_n11_), .B(i7), .C(i1), .Y(new_n48_));
  OAI210     g38(.A0(new_n48_), .A1(new_n47_), .B0(i0), .Y(new_n49_));
  NO2        g39(.A(new_n26_), .B(i0), .Y(new_n50_));
  NA3        g40(.A(i7), .B(i6), .C(new_n20_), .Y(new_n51_));
  OAI210     g41(.A0(i6), .A1(new_n13_), .B0(new_n51_), .Y(new_n52_));
  AOI220     g42(.A0(new_n11_), .A1(new_n20_), .B0(new_n15_), .B1(new_n33_), .Y(new_n53_));
  NO3        g43(.A(new_n53_), .B(new_n25_), .C(new_n13_), .Y(new_n54_));
  AOI210     g44(.A0(new_n52_), .A1(new_n50_), .B0(new_n54_), .Y(new_n55_));
  NA4        g45(.A(new_n55_), .B(new_n49_), .C(new_n46_), .D(new_n37_), .Y(new_n56_));
  AO210      g46(.A0(new_n32_), .A1(i3), .B0(new_n56_), .Y(zori0));
endmodule


