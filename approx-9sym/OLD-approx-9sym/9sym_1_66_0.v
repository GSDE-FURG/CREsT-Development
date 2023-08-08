// Benchmark "9sym/9sym_1_66_0_esp" written by ABC on Wed Mar  8 02:32:43 2023

module \9sym/9sym_1_66_0_esp  ( 
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
    new_n53_, new_n54_, new_n55_, new_n56_, new_n57_, new_n58_, new_n59_,
    new_n60_;
  NAi21      g00(.An(i4), .B(i5), .Y(new_n11_));
  NAi31      g01(.An(i2), .B(i8), .C(i6), .Y(new_n12_));
  AOI210     g02(.A0(new_n12_), .A1(new_n11_), .B0(i1), .Y(new_n13_));
  INV        g03(.A(i4), .Y(new_n14_));
  NO2        g04(.A(i6), .B(new_n14_), .Y(new_n15_));
  OAI210     g05(.A0(new_n15_), .A1(new_n13_), .B0(i7), .Y(new_n16_));
  NOi21      g06(.An(i6), .B(i7), .Y(new_n17_));
  INV        g07(.A(i2), .Y(new_n18_));
  NA2        g08(.A(i5), .B(i1), .Y(new_n19_));
  OAI220     g09(.A0(new_n19_), .A1(i7), .B0(i6), .B1(new_n18_), .Y(new_n20_));
  AOI220     g10(.A0(new_n20_), .A1(i0), .B0(new_n17_), .B1(i4), .Y(new_n21_));
  AO210      g11(.A0(new_n21_), .A1(new_n16_), .B0(i3), .Y(new_n22_));
  INV        g12(.A(i8), .Y(new_n23_));
  NOi21      g13(.An(i5), .B(i4), .Y(new_n24_));
  NO2        g14(.A(i7), .B(i1), .Y(new_n25_));
  AOI220     g15(.A0(new_n25_), .A1(i6), .B0(new_n24_), .B1(new_n23_), .Y(new_n26_));
  INV        g16(.A(i6), .Y(new_n27_));
  OAI210     g17(.A0(i7), .A1(i2), .B0(i5), .Y(new_n28_));
  NA3        g18(.A(new_n28_), .B(new_n27_), .C(i0), .Y(new_n29_));
  OAI210     g19(.A0(new_n26_), .A1(new_n18_), .B0(new_n29_), .Y(new_n30_));
  INV        g20(.A(i5), .Y(new_n31_));
  NOi21      g21(.An(i5), .B(i8), .Y(new_n32_));
  NOi21      g22(.An(i7), .B(i1), .Y(new_n33_));
  AOI220     g23(.A0(new_n33_), .A1(new_n31_), .B0(new_n32_), .B1(new_n17_), .Y(new_n34_));
  AOI210     g24(.A0(i8), .A1(i5), .B0(i1), .Y(new_n35_));
  AN3        g25(.A(i8), .B(i5), .C(i1), .Y(new_n36_));
  AOI210     g26(.A0(i5), .A1(i2), .B0(i0), .Y(new_n37_));
  NAi21      g27(.An(new_n36_), .B(new_n37_), .Y(new_n38_));
  OAI220     g28(.A0(new_n38_), .A1(new_n35_), .B0(new_n34_), .B1(i2), .Y(new_n39_));
  OAI210     g29(.A0(new_n39_), .A1(new_n30_), .B0(i3), .Y(new_n40_));
  INV        g30(.A(i1), .Y(new_n41_));
  NA2        g31(.A(i6), .B(new_n18_), .Y(new_n42_));
  NA2        g32(.A(new_n32_), .B(i2), .Y(new_n43_));
  AOI210     g33(.A0(new_n43_), .A1(new_n42_), .B0(new_n41_), .Y(new_n44_));
  NA3        g34(.A(i7), .B(i5), .C(new_n18_), .Y(new_n45_));
  INV        g35(.A(i0), .Y(new_n46_));
  NA3        g36(.A(i8), .B(i2), .C(new_n46_), .Y(new_n47_));
  NA2        g37(.A(new_n47_), .B(new_n45_), .Y(new_n48_));
  OAI210     g38(.A0(new_n48_), .A1(new_n44_), .B0(new_n14_), .Y(new_n49_));
  NA3        g39(.A(i7), .B(new_n31_), .C(i2), .Y(new_n50_));
  OAI210     g40(.A0(new_n36_), .A1(i6), .B0(new_n46_), .Y(new_n51_));
  AOI210     g41(.A0(new_n50_), .A1(i6), .B0(new_n51_), .Y(new_n52_));
  NA3        g42(.A(new_n27_), .B(i2), .C(new_n46_), .Y(new_n53_));
  NOi21      g43(.An(i6), .B(i8), .Y(new_n54_));
  NA2        g44(.A(new_n54_), .B(new_n31_), .Y(new_n55_));
  AOI210     g45(.A0(new_n55_), .A1(new_n53_), .B0(new_n14_), .Y(new_n56_));
  AOI220     g46(.A0(new_n54_), .A1(new_n14_), .B0(new_n25_), .B1(i8), .Y(new_n57_));
  NA2        g47(.A(new_n33_), .B(new_n32_), .Y(new_n58_));
  OAI210     g48(.A0(new_n57_), .A1(new_n46_), .B0(new_n58_), .Y(new_n59_));
  NO3        g49(.A(new_n59_), .B(new_n56_), .C(new_n52_), .Y(new_n60_));
  NA4        g50(.A(new_n60_), .B(new_n49_), .C(new_n40_), .D(new_n22_), .Y(zori0));
endmodule


