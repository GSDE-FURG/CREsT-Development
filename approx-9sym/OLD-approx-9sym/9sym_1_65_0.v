// Benchmark "9sym/9sym_1_65_0_esp" written by ABC on Wed Mar  8 02:32:31 2023

module \9sym/9sym_1_65_0_esp  ( 
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
    new_n60_, new_n61_, new_n62_, new_n63_;
  NOi21      g00(.An(i4), .B(i6), .Y(new_n11_));
  NAi21      g01(.An(i4), .B(i5), .Y(new_n12_));
  NAi31      g02(.An(i2), .B(i8), .C(i6), .Y(new_n13_));
  AOI210     g03(.A0(new_n13_), .A1(new_n12_), .B0(i1), .Y(new_n14_));
  OAI210     g04(.A0(new_n14_), .A1(new_n11_), .B0(i7), .Y(new_n15_));
  INV        g05(.A(i6), .Y(new_n16_));
  NA2        g06(.A(i6), .B(i4), .Y(new_n17_));
  NA3        g07(.A(i5), .B(i1), .C(i0), .Y(new_n18_));
  AOI210     g08(.A0(new_n18_), .A1(new_n17_), .B0(i7), .Y(new_n19_));
  AN2        g09(.A(i2), .B(i0), .Y(new_n20_));
  AOI210     g10(.A0(new_n20_), .A1(new_n16_), .B0(new_n19_), .Y(new_n21_));
  AOI210     g11(.A0(new_n21_), .A1(new_n15_), .B0(i3), .Y(new_n22_));
  NAi31      g12(.An(i7), .B(i8), .C(i4), .Y(new_n23_));
  NAi31      g13(.An(i1), .B(i7), .C(i3), .Y(new_n24_));
  AOI210     g14(.A0(new_n24_), .A1(new_n23_), .B0(i2), .Y(new_n25_));
  NA2        g15(.A(i3), .B(i1), .Y(new_n26_));
  NA3        g16(.A(i7), .B(i6), .C(i2), .Y(new_n27_));
  AOI210     g17(.A0(new_n27_), .A1(new_n26_), .B0(i0), .Y(new_n28_));
  NAi31      g18(.An(i6), .B(i3), .C(i0), .Y(new_n29_));
  OAI210     g19(.A0(new_n17_), .A1(i8), .B0(new_n29_), .Y(new_n30_));
  NO3        g20(.A(new_n30_), .B(new_n28_), .C(new_n25_), .Y(new_n31_));
  NO2        g21(.A(new_n31_), .B(i5), .Y(new_n32_));
  INV        g22(.A(i5), .Y(new_n33_));
  INV        g23(.A(i8), .Y(new_n34_));
  INV        g24(.A(i2), .Y(new_n35_));
  NO2        g25(.A(i3), .B(i1), .Y(new_n36_));
  NO3        g26(.A(new_n36_), .B(i4), .C(new_n35_), .Y(new_n37_));
  INV        g27(.A(i7), .Y(new_n38_));
  NAi21      g28(.An(i2), .B(i3), .Y(new_n39_));
  NAi21      g29(.An(i7), .B(i6), .Y(new_n40_));
  OAI220     g30(.A0(new_n40_), .A1(new_n39_), .B0(new_n38_), .B1(i1), .Y(new_n41_));
  OAI210     g31(.A0(new_n41_), .A1(new_n37_), .B0(new_n34_), .Y(new_n42_));
  INV        g32(.A(i1), .Y(new_n43_));
  NA2        g33(.A(new_n39_), .B(new_n43_), .Y(new_n44_));
  AN2        g34(.A(i6), .B(i1), .Y(new_n45_));
  NO3        g35(.A(new_n45_), .B(new_n34_), .C(i0), .Y(new_n46_));
  NO2        g36(.A(i4), .B(i2), .Y(new_n47_));
  AOI220     g37(.A0(new_n47_), .A1(i7), .B0(new_n46_), .B1(new_n44_), .Y(new_n48_));
  AOI210     g38(.A0(new_n48_), .A1(new_n42_), .B0(new_n33_), .Y(new_n49_));
  NAi21      g39(.An(i4), .B(i6), .Y(new_n50_));
  NAi32      g40(.An(i8), .Bn(i0), .C(i3), .Y(new_n51_));
  AOI210     g41(.A0(new_n51_), .A1(new_n50_), .B0(new_n43_), .Y(new_n52_));
  NO2        g42(.A(new_n29_), .B(i7), .Y(new_n53_));
  OAI210     g43(.A0(new_n53_), .A1(new_n52_), .B0(new_n35_), .Y(new_n54_));
  NO3        g44(.A(new_n34_), .B(i7), .C(i1), .Y(new_n55_));
  NO2        g45(.A(new_n50_), .B(i8), .Y(new_n56_));
  OAI210     g46(.A0(new_n56_), .A1(new_n55_), .B0(i0), .Y(new_n57_));
  INV        g47(.A(i3), .Y(new_n58_));
  NO3        g48(.A(new_n40_), .B(new_n58_), .C(i1), .Y(new_n59_));
  NO2        g49(.A(i8), .B(i4), .Y(new_n60_));
  NOi31      g50(.An(new_n17_), .B(new_n60_), .C(i0), .Y(new_n61_));
  OAI210     g51(.A0(new_n61_), .A1(new_n59_), .B0(i2), .Y(new_n62_));
  NA3        g52(.A(new_n62_), .B(new_n57_), .C(new_n54_), .Y(new_n63_));
  OR4        g53(.A(new_n63_), .B(new_n49_), .C(new_n32_), .D(new_n22_), .Y(zori0));
endmodule


