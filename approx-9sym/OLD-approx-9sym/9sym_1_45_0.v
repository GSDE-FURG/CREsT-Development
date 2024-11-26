// Benchmark "9sym/9sym_1_45_0_esp" written by ABC on Wed Mar  8 02:27:32 2023

module \9sym/9sym_1_45_0_esp  ( 
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
    new_n67_, new_n68_, new_n69_, new_n70_, new_n71_, new_n72_;
  INV        g00(.A(i2), .Y(new_n11_));
  NAi21      g01(.An(i5), .B(i3), .Y(new_n12_));
  NAi31      g02(.An(i3), .B(i8), .C(i6), .Y(new_n13_));
  AOI210     g03(.A0(new_n13_), .A1(new_n12_), .B0(i1), .Y(new_n14_));
  INV        g04(.A(i5), .Y(new_n15_));
  NO2        g05(.A(new_n15_), .B(i4), .Y(new_n16_));
  OAI210     g06(.A0(new_n16_), .A1(new_n14_), .B0(i7), .Y(new_n17_));
  INV        g07(.A(i6), .Y(new_n18_));
  NO2        g08(.A(new_n18_), .B(i4), .Y(new_n19_));
  INV        g09(.A(i7), .Y(new_n20_));
  INV        g10(.A(i8), .Y(new_n21_));
  NO4        g11(.A(new_n21_), .B(new_n20_), .C(i5), .D(i3), .Y(new_n22_));
  OAI210     g12(.A0(new_n22_), .A1(new_n19_), .B0(i1), .Y(new_n23_));
  NAi21      g13(.An(i5), .B(i4), .Y(new_n24_));
  NAi21      g14(.An(i8), .B(i3), .Y(new_n25_));
  NA2        g15(.A(i6), .B(i5), .Y(new_n26_));
  OAI220     g16(.A0(new_n26_), .A1(new_n25_), .B0(new_n24_), .B1(new_n21_), .Y(new_n27_));
  INV        g17(.A(i3), .Y(new_n28_));
  OAI220     g18(.A0(new_n21_), .A1(i1), .B0(i7), .B1(new_n28_), .Y(new_n29_));
  INV        g19(.A(i0), .Y(new_n30_));
  NO2        g20(.A(i6), .B(new_n30_), .Y(new_n31_));
  AOI220     g21(.A0(new_n31_), .A1(new_n29_), .B0(new_n27_), .B1(new_n20_), .Y(new_n32_));
  NA3        g22(.A(new_n32_), .B(new_n23_), .C(new_n17_), .Y(new_n33_));
  NA2        g23(.A(new_n33_), .B(new_n11_), .Y(new_n34_));
  NO3        g24(.A(new_n21_), .B(i5), .C(i4), .Y(new_n35_));
  OAI210     g25(.A0(new_n35_), .A1(new_n31_), .B0(i2), .Y(new_n36_));
  NOi21      g26(.An(i4), .B(i6), .Y(new_n37_));
  NA2        g27(.A(new_n37_), .B(i7), .Y(new_n38_));
  AOI210     g28(.A0(new_n38_), .A1(new_n36_), .B0(i3), .Y(new_n39_));
  NA2        g29(.A(i4), .B(new_n28_), .Y(new_n40_));
  INV        g30(.A(i1), .Y(new_n41_));
  NA3        g31(.A(i3), .B(i2), .C(new_n41_), .Y(new_n42_));
  AO210      g32(.A0(new_n42_), .A1(new_n40_), .B0(new_n18_), .Y(new_n43_));
  OAI210     g33(.A0(i6), .A1(i4), .B0(i1), .Y(new_n44_));
  NA3        g34(.A(new_n44_), .B(i8), .C(i0), .Y(new_n45_));
  AOI210     g35(.A0(new_n45_), .A1(new_n43_), .B0(i7), .Y(new_n46_));
  NO2        g36(.A(new_n12_), .B(i6), .Y(new_n47_));
  AOI210     g37(.A0(new_n19_), .A1(new_n21_), .B0(new_n47_), .Y(new_n48_));
  NA2        g38(.A(new_n21_), .B(i6), .Y(new_n49_));
  OAI220     g39(.A0(new_n49_), .A1(new_n24_), .B0(new_n48_), .B1(new_n30_), .Y(new_n50_));
  NO3        g40(.A(new_n50_), .B(new_n46_), .C(new_n39_), .Y(new_n51_));
  OAI210     g41(.A0(i8), .A1(i2), .B0(i5), .Y(new_n52_));
  NA2        g42(.A(new_n52_), .B(i1), .Y(new_n53_));
  NA4        g43(.A(i8), .B(i5), .C(new_n11_), .D(new_n41_), .Y(new_n54_));
  AOI210     g44(.A0(new_n54_), .A1(new_n53_), .B0(new_n28_), .Y(new_n55_));
  NA3        g45(.A(i7), .B(i6), .C(new_n15_), .Y(new_n56_));
  INV        g46(.A(i4), .Y(new_n57_));
  AOI210     g47(.A0(i8), .A1(new_n57_), .B0(new_n37_), .Y(new_n58_));
  AOI210     g48(.A0(new_n58_), .A1(new_n56_), .B0(new_n11_), .Y(new_n59_));
  NA2        g49(.A(i8), .B(i6), .Y(new_n60_));
  NA3        g50(.A(new_n60_), .B(i5), .C(i1), .Y(new_n61_));
  AOI210     g51(.A0(new_n40_), .A1(new_n21_), .B0(new_n61_), .Y(new_n62_));
  OR3        g52(.A(new_n62_), .B(new_n59_), .C(new_n55_), .Y(new_n63_));
  NO3        g53(.A(i8), .B(i4), .C(new_n11_), .Y(new_n64_));
  NO3        g54(.A(i7), .B(i3), .C(new_n30_), .Y(new_n65_));
  OAI210     g55(.A0(new_n65_), .A1(new_n64_), .B0(i1), .Y(new_n66_));
  AOI220     g56(.A0(new_n37_), .A1(new_n20_), .B0(new_n57_), .B1(i2), .Y(new_n67_));
  AOI210     g57(.A0(new_n57_), .A1(new_n28_), .B0(new_n21_), .Y(new_n68_));
  NA2        g58(.A(i7), .B(new_n41_), .Y(new_n69_));
  OA220      g59(.A0(new_n69_), .A1(new_n68_), .B0(new_n67_), .B1(new_n25_), .Y(new_n70_));
  AOI210     g60(.A0(new_n70_), .A1(new_n66_), .B0(new_n15_), .Y(new_n71_));
  AOI210     g61(.A0(new_n63_), .A1(new_n30_), .B0(new_n71_), .Y(new_n72_));
  NA3        g62(.A(new_n72_), .B(new_n51_), .C(new_n34_), .Y(zori0));
endmodule


