// Benchmark "9sym/9sym_1_49_0_esp" written by ABC on Wed Mar  8 02:28:10 2023

module \9sym/9sym_1_49_0_esp  ( 
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
    new_n60_, new_n61_, new_n62_, new_n63_, new_n64_, new_n65_, new_n66_,
    new_n67_;
  INV        g00(.A(i5), .Y(new_n11_));
  INV        g01(.A(i8), .Y(new_n12_));
  NAi21      g02(.An(i2), .B(i6), .Y(new_n13_));
  OA220      g03(.A0(new_n13_), .A1(new_n12_), .B0(new_n11_), .B1(i4), .Y(new_n14_));
  INV        g04(.A(i6), .Y(new_n15_));
  NO2        g05(.A(i5), .B(i2), .Y(new_n16_));
  INV        g06(.A(i1), .Y(new_n17_));
  NO2        g07(.A(new_n12_), .B(new_n17_), .Y(new_n18_));
  AOI220     g08(.A0(new_n18_), .A1(new_n16_), .B0(new_n15_), .B1(i4), .Y(new_n19_));
  OAI210     g09(.A0(new_n14_), .A1(i1), .B0(new_n19_), .Y(new_n20_));
  INV        g10(.A(i7), .Y(new_n21_));
  INV        g11(.A(i4), .Y(new_n22_));
  NO3        g12(.A(i8), .B(new_n22_), .C(i0), .Y(new_n23_));
  AOI210     g13(.A0(new_n21_), .A1(i0), .B0(new_n23_), .Y(new_n24_));
  NA2        g14(.A(i5), .B(i1), .Y(new_n25_));
  NO2        g15(.A(i7), .B(new_n15_), .Y(new_n26_));
  OAI210     g16(.A0(i5), .A1(i4), .B0(i6), .Y(new_n27_));
  INV        g17(.A(i0), .Y(new_n28_));
  INV        g18(.A(i2), .Y(new_n29_));
  NO2        g19(.A(new_n29_), .B(new_n28_), .Y(new_n30_));
  AOI220     g20(.A0(new_n30_), .A1(new_n27_), .B0(new_n26_), .B1(i4), .Y(new_n31_));
  OAI210     g21(.A0(new_n25_), .A1(new_n24_), .B0(new_n31_), .Y(new_n32_));
  AOI210     g22(.A0(new_n20_), .A1(i7), .B0(new_n32_), .Y(new_n33_));
  NO2        g23(.A(i7), .B(i2), .Y(new_n34_));
  OA210      g24(.A0(new_n34_), .A1(new_n11_), .B0(i0), .Y(new_n35_));
  NAi21      g25(.An(i8), .B(i5), .Y(new_n36_));
  NO3        g26(.A(new_n36_), .B(i7), .C(new_n22_), .Y(new_n37_));
  OAI210     g27(.A0(new_n37_), .A1(new_n35_), .B0(new_n15_), .Y(new_n38_));
  NA3        g28(.A(i8), .B(i5), .C(i1), .Y(new_n39_));
  OAI210     g29(.A0(new_n12_), .A1(new_n11_), .B0(new_n17_), .Y(new_n40_));
  AOI210     g30(.A0(i5), .A1(i2), .B0(i0), .Y(new_n41_));
  NA3        g31(.A(new_n41_), .B(new_n40_), .C(new_n39_), .Y(new_n42_));
  NO2        g32(.A(new_n34_), .B(i1), .Y(new_n43_));
  OAI210     g33(.A0(new_n26_), .A1(new_n16_), .B0(new_n43_), .Y(new_n44_));
  NA2        g34(.A(i4), .B(i2), .Y(new_n45_));
  NOi21      g35(.An(new_n45_), .B(new_n36_), .Y(new_n46_));
  OAI210     g36(.A0(new_n26_), .A1(i2), .B0(new_n46_), .Y(new_n47_));
  NA4        g37(.A(new_n47_), .B(new_n44_), .C(new_n42_), .D(new_n38_), .Y(new_n48_));
  NAi31      g38(.An(i8), .B(i5), .C(i2), .Y(new_n49_));
  AOI210     g39(.A0(new_n49_), .A1(new_n13_), .B0(new_n17_), .Y(new_n50_));
  NO3        g40(.A(new_n21_), .B(new_n11_), .C(i2), .Y(new_n51_));
  OAI210     g41(.A0(new_n51_), .A1(new_n50_), .B0(new_n22_), .Y(new_n52_));
  AOI210     g42(.A0(new_n45_), .A1(new_n39_), .B0(i6), .Y(new_n53_));
  NAi21      g43(.An(i4), .B(i8), .Y(new_n54_));
  NAi31      g44(.An(i5), .B(i7), .C(i6), .Y(new_n55_));
  AOI210     g45(.A0(new_n55_), .A1(new_n54_), .B0(new_n29_), .Y(new_n56_));
  OAI210     g46(.A0(new_n56_), .A1(new_n53_), .B0(new_n28_), .Y(new_n57_));
  NAi21      g47(.An(i8), .B(i6), .Y(new_n58_));
  NA2        g48(.A(new_n34_), .B(i8), .Y(new_n59_));
  NA2        g49(.A(new_n11_), .B(i4), .Y(new_n60_));
  AO210      g50(.A0(new_n59_), .A1(new_n58_), .B0(new_n60_), .Y(new_n61_));
  OR2        g51(.A(i7), .B(i1), .Y(new_n62_));
  OAI220     g52(.A0(new_n62_), .A1(new_n12_), .B0(new_n58_), .B1(i4), .Y(new_n63_));
  NO3        g53(.A(new_n36_), .B(new_n21_), .C(i1), .Y(new_n64_));
  AOI210     g54(.A0(new_n63_), .A1(i0), .B0(new_n64_), .Y(new_n65_));
  NA4        g55(.A(new_n65_), .B(new_n61_), .C(new_n57_), .D(new_n52_), .Y(new_n66_));
  AOI210     g56(.A0(new_n48_), .A1(i3), .B0(new_n66_), .Y(new_n67_));
  OAI210     g57(.A0(new_n33_), .A1(i3), .B0(new_n67_), .Y(zori0));
endmodule


