// Benchmark "9sym/9sym_1_60_0_esp" written by ABC on Wed Mar  8 02:29:50 2023

module \9sym/9sym_1_60_0_esp  ( 
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
    new_n60_, new_n61_, new_n62_, new_n63_, new_n64_;
  NAi21      g00(.An(i4), .B(i5), .Y(new_n11_));
  NAi31      g01(.An(i2), .B(i8), .C(i6), .Y(new_n12_));
  AOI210     g02(.A0(new_n12_), .A1(new_n11_), .B0(i1), .Y(new_n13_));
  INV        g03(.A(i4), .Y(new_n14_));
  NAi21      g04(.An(i2), .B(i8), .Y(new_n15_));
  NAi21      g05(.An(i5), .B(i1), .Y(new_n16_));
  OAI220     g06(.A0(new_n16_), .A1(new_n15_), .B0(i6), .B1(new_n14_), .Y(new_n17_));
  OAI210     g07(.A0(new_n17_), .A1(new_n13_), .B0(i7), .Y(new_n18_));
  INV        g08(.A(i2), .Y(new_n19_));
  NA2        g09(.A(i5), .B(i1), .Y(new_n20_));
  OAI220     g10(.A0(new_n20_), .A1(i7), .B0(i6), .B1(new_n19_), .Y(new_n21_));
  NA2        g11(.A(i6), .B(i4), .Y(new_n22_));
  NO2        g12(.A(new_n22_), .B(i7), .Y(new_n23_));
  AOI210     g13(.A0(new_n21_), .A1(i0), .B0(new_n23_), .Y(new_n24_));
  AOI210     g14(.A0(new_n24_), .A1(new_n18_), .B0(i3), .Y(new_n25_));
  NAi31      g15(.An(i7), .B(i8), .C(i4), .Y(new_n26_));
  NAi31      g16(.An(i1), .B(i7), .C(i3), .Y(new_n27_));
  AOI210     g17(.A0(new_n27_), .A1(new_n26_), .B0(i2), .Y(new_n28_));
  NA2        g18(.A(i3), .B(i1), .Y(new_n29_));
  NA3        g19(.A(i7), .B(i6), .C(i2), .Y(new_n30_));
  AOI210     g20(.A0(new_n30_), .A1(new_n29_), .B0(i0), .Y(new_n31_));
  NA2        g21(.A(i3), .B(i0), .Y(new_n32_));
  NO2        g22(.A(new_n32_), .B(i6), .Y(new_n33_));
  NO2        g23(.A(new_n22_), .B(i8), .Y(new_n34_));
  NO4        g24(.A(new_n34_), .B(new_n33_), .C(new_n31_), .D(new_n28_), .Y(new_n35_));
  NO2        g25(.A(new_n35_), .B(i5), .Y(new_n36_));
  INV        g26(.A(i5), .Y(new_n37_));
  INV        g27(.A(i8), .Y(new_n38_));
  NO2        g28(.A(i3), .B(i1), .Y(new_n39_));
  NO3        g29(.A(new_n39_), .B(i4), .C(new_n19_), .Y(new_n40_));
  INV        g30(.A(i7), .Y(new_n41_));
  NAi21      g31(.An(i2), .B(i3), .Y(new_n42_));
  NAi21      g32(.An(i7), .B(i6), .Y(new_n43_));
  OAI220     g33(.A0(new_n43_), .A1(new_n42_), .B0(new_n41_), .B1(i1), .Y(new_n44_));
  OAI210     g34(.A0(new_n44_), .A1(new_n40_), .B0(new_n38_), .Y(new_n45_));
  INV        g35(.A(i1), .Y(new_n46_));
  NA2        g36(.A(new_n42_), .B(new_n46_), .Y(new_n47_));
  AN2        g37(.A(i6), .B(i1), .Y(new_n48_));
  NO3        g38(.A(new_n48_), .B(new_n38_), .C(i0), .Y(new_n49_));
  NO2        g39(.A(i4), .B(i2), .Y(new_n50_));
  AOI220     g40(.A0(new_n50_), .A1(i7), .B0(new_n49_), .B1(new_n47_), .Y(new_n51_));
  AOI210     g41(.A0(new_n51_), .A1(new_n45_), .B0(new_n37_), .Y(new_n52_));
  NAi21      g42(.An(i4), .B(i6), .Y(new_n53_));
  NAi21      g43(.An(i0), .B(i3), .Y(new_n54_));
  OAI210     g44(.A0(new_n54_), .A1(i8), .B0(new_n53_), .Y(new_n55_));
  AOI220     g45(.A0(new_n55_), .A1(i1), .B0(new_n33_), .B1(new_n41_), .Y(new_n56_));
  NAi21      g46(.An(i7), .B(i8), .Y(new_n57_));
  OAI220     g47(.A0(new_n53_), .A1(i8), .B0(new_n57_), .B1(i1), .Y(new_n58_));
  NAi21      g48(.An(i1), .B(i3), .Y(new_n59_));
  NO2        g49(.A(i8), .B(i4), .Y(new_n60_));
  AO210      g50(.A0(i6), .A1(i4), .B0(i0), .Y(new_n61_));
  OAI220     g51(.A0(new_n61_), .A1(new_n60_), .B0(new_n59_), .B1(new_n43_), .Y(new_n62_));
  AOI220     g52(.A0(new_n62_), .A1(i2), .B0(new_n58_), .B1(i0), .Y(new_n63_));
  OAI210     g53(.A0(new_n56_), .A1(i2), .B0(new_n63_), .Y(new_n64_));
  OR4        g54(.A(new_n64_), .B(new_n52_), .C(new_n36_), .D(new_n25_), .Y(zori0));
endmodule


