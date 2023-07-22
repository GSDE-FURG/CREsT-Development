// Benchmark "9sym/9sym_1_39_0_esp" written by ABC on Wed Mar  8 02:26:03 2023

module \9sym/9sym_1_39_0_esp  ( 
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
    new_n67_, new_n68_, new_n69_, new_n70_, new_n71_, new_n72_, new_n73_,
    new_n74_, new_n75_, new_n76_;
  OAI210     g00(.A0(i6), .A1(i2), .B0(i7), .Y(new_n11_));
  NA2        g01(.A(new_n11_), .B(i0), .Y(new_n12_));
  NOi21      g02(.An(i5), .B(i0), .Y(new_n13_));
  NA2        g03(.A(new_n13_), .B(i3), .Y(new_n14_));
  NOi21      g04(.An(i7), .B(i3), .Y(new_n15_));
  NA2        g05(.A(new_n15_), .B(i6), .Y(new_n16_));
  AO210      g06(.A0(new_n16_), .A1(new_n14_), .B0(i2), .Y(new_n17_));
  AOI210     g07(.A0(new_n17_), .A1(new_n12_), .B0(i1), .Y(new_n18_));
  OAI210     g08(.A0(i5), .A1(i3), .B0(i0), .Y(new_n19_));
  INV        g09(.A(i0), .Y(new_n20_));
  NO3        g10(.A(i7), .B(i6), .C(new_n20_), .Y(new_n21_));
  AOI210     g11(.A0(new_n19_), .A1(i2), .B0(new_n21_), .Y(new_n22_));
  INV        g12(.A(i1), .Y(new_n23_));
  INV        g13(.A(i6), .Y(new_n24_));
  NO2        g14(.A(i5), .B(i2), .Y(new_n25_));
  AOI220     g15(.A0(new_n25_), .A1(new_n15_), .B0(new_n13_), .B1(new_n24_), .Y(new_n26_));
  INV        g16(.A(i2), .Y(new_n27_));
  OAI220     g17(.A0(i7), .A1(i5), .B0(i3), .B1(i0), .Y(new_n28_));
  NA3        g18(.A(new_n28_), .B(i4), .C(new_n27_), .Y(new_n29_));
  OA210      g19(.A0(new_n26_), .A1(new_n23_), .B0(new_n29_), .Y(new_n30_));
  OAI210     g20(.A0(new_n22_), .A1(i4), .B0(new_n30_), .Y(new_n31_));
  OAI210     g21(.A0(new_n31_), .A1(new_n18_), .B0(i8), .Y(new_n32_));
  NAi21      g22(.An(i4), .B(i5), .Y(new_n33_));
  NAi21      g23(.An(i3), .B(i0), .Y(new_n34_));
  OR3        g24(.A(new_n34_), .B(i8), .C(new_n23_), .Y(new_n35_));
  AOI210     g25(.A0(new_n35_), .A1(new_n33_), .B0(i2), .Y(new_n36_));
  INV        g26(.A(i4), .Y(new_n37_));
  OA220      g27(.A0(new_n33_), .A1(i3), .B0(i8), .B1(new_n37_), .Y(new_n38_));
  NO3        g28(.A(i6), .B(new_n37_), .C(i3), .Y(new_n39_));
  NO2        g29(.A(new_n27_), .B(i0), .Y(new_n40_));
  NO2        g30(.A(new_n24_), .B(i5), .Y(new_n41_));
  AOI210     g31(.A0(new_n41_), .A1(new_n40_), .B0(new_n39_), .Y(new_n42_));
  OAI210     g32(.A0(new_n38_), .A1(i1), .B0(new_n42_), .Y(new_n43_));
  OAI210     g33(.A0(new_n43_), .A1(new_n36_), .B0(i7), .Y(new_n44_));
  INV        g34(.A(i5), .Y(new_n45_));
  NO2        g35(.A(i7), .B(i2), .Y(new_n46_));
  OA210      g36(.A0(new_n46_), .A1(new_n45_), .B0(i0), .Y(new_n47_));
  NO4        g37(.A(i8), .B(i7), .C(new_n45_), .D(new_n37_), .Y(new_n48_));
  OAI210     g38(.A0(new_n48_), .A1(new_n47_), .B0(new_n24_), .Y(new_n49_));
  NAi21      g39(.An(i7), .B(i6), .Y(new_n50_));
  NA2        g40(.A(new_n50_), .B(new_n27_), .Y(new_n51_));
  NA2        g41(.A(i4), .B(i2), .Y(new_n52_));
  NO2        g42(.A(i8), .B(new_n45_), .Y(new_n53_));
  NA3        g43(.A(new_n53_), .B(new_n52_), .C(new_n51_), .Y(new_n54_));
  NAi21      g44(.An(new_n25_), .B(new_n50_), .Y(new_n55_));
  NO2        g45(.A(new_n46_), .B(i1), .Y(new_n56_));
  NA2        g46(.A(new_n56_), .B(new_n55_), .Y(new_n57_));
  OAI210     g47(.A0(i8), .A1(i2), .B0(i5), .Y(new_n58_));
  NA3        g48(.A(new_n58_), .B(i1), .C(new_n20_), .Y(new_n59_));
  NA4        g49(.A(new_n59_), .B(new_n57_), .C(new_n54_), .D(new_n49_), .Y(new_n60_));
  NAi31      g50(.An(i3), .B(i5), .C(i4), .Y(new_n61_));
  OR2        g51(.A(i8), .B(i0), .Y(new_n62_));
  AOI210     g52(.A0(new_n61_), .A1(new_n50_), .B0(new_n62_), .Y(new_n63_));
  NO3        g53(.A(new_n24_), .B(i4), .C(i2), .Y(new_n64_));
  OAI210     g54(.A0(new_n64_), .A1(new_n63_), .B0(i1), .Y(new_n65_));
  NAi21      g55(.An(i0), .B(i4), .Y(new_n66_));
  AOI210     g56(.A0(new_n66_), .A1(new_n34_), .B0(i6), .Y(new_n67_));
  NO3        g57(.A(new_n33_), .B(i8), .C(new_n23_), .Y(new_n68_));
  OAI210     g58(.A0(new_n68_), .A1(new_n67_), .B0(i2), .Y(new_n69_));
  NO3        g59(.A(i8), .B(new_n24_), .C(i4), .Y(new_n70_));
  NO4        g60(.A(i7), .B(new_n45_), .C(i3), .D(new_n23_), .Y(new_n71_));
  OAI210     g61(.A0(new_n71_), .A1(new_n70_), .B0(i0), .Y(new_n72_));
  OAI220     g62(.A0(i8), .A1(i5), .B0(i7), .B1(i3), .Y(new_n73_));
  NA3        g63(.A(new_n73_), .B(i6), .C(i4), .Y(new_n74_));
  NA4        g64(.A(new_n74_), .B(new_n72_), .C(new_n69_), .D(new_n65_), .Y(new_n75_));
  AOI210     g65(.A0(new_n60_), .A1(i3), .B0(new_n75_), .Y(new_n76_));
  NA3        g66(.A(new_n76_), .B(new_n44_), .C(new_n32_), .Y(zori0));
endmodule


