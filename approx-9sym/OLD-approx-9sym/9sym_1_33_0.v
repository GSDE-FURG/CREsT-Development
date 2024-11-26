// Benchmark "9sym/9sym_1_33_0_esp" written by ABC on Wed Mar  8 02:24:48 2023

module \9sym/9sym_1_33_0_esp  ( 
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
    new_n74_, new_n75_, new_n76_, new_n77_, new_n78_, new_n79_;
  OAI210     g00(.A0(i5), .A1(i3), .B0(i0), .Y(new_n11_));
  NA2        g01(.A(new_n11_), .B(i2), .Y(new_n12_));
  INV        g02(.A(i5), .Y(new_n13_));
  NA3        g03(.A(i6), .B(new_n13_), .C(i3), .Y(new_n14_));
  INV        g04(.A(i6), .Y(new_n15_));
  NA2        g05(.A(new_n15_), .B(i0), .Y(new_n16_));
  AO210      g06(.A0(new_n16_), .A1(new_n14_), .B0(i7), .Y(new_n17_));
  AOI210     g07(.A0(new_n17_), .A1(new_n12_), .B0(i4), .Y(new_n18_));
  OAI210     g08(.A0(i6), .A1(i2), .B0(i7), .Y(new_n19_));
  NAi21      g09(.An(i0), .B(i5), .Y(new_n20_));
  NA2        g10(.A(new_n20_), .B(i3), .Y(new_n21_));
  NO2        g11(.A(i6), .B(i3), .Y(new_n22_));
  NO2        g12(.A(i7), .B(i3), .Y(new_n23_));
  NO3        g13(.A(new_n23_), .B(new_n22_), .C(i2), .Y(new_n24_));
  AOI220     g14(.A0(new_n24_), .A1(new_n21_), .B0(new_n19_), .B1(i0), .Y(new_n25_));
  NAi21      g15(.An(i5), .B(i7), .Y(new_n26_));
  OR2        g16(.A(i3), .B(i2), .Y(new_n27_));
  OAI220     g17(.A0(new_n27_), .A1(new_n26_), .B0(new_n20_), .B1(i6), .Y(new_n28_));
  OAI220     g18(.A0(i7), .A1(i5), .B0(i3), .B1(i0), .Y(new_n29_));
  NOi21      g19(.An(i4), .B(i2), .Y(new_n30_));
  AOI220     g20(.A0(new_n30_), .A1(new_n29_), .B0(new_n28_), .B1(i1), .Y(new_n31_));
  OAI210     g21(.A0(new_n25_), .A1(i1), .B0(new_n31_), .Y(new_n32_));
  OAI210     g22(.A0(new_n32_), .A1(new_n18_), .B0(i8), .Y(new_n33_));
  NAi21      g23(.An(i2), .B(i3), .Y(new_n34_));
  INV        g24(.A(i3), .Y(new_n35_));
  NA3        g25(.A(i5), .B(i4), .C(new_n35_), .Y(new_n36_));
  AOI210     g26(.A0(new_n36_), .A1(new_n34_), .B0(i0), .Y(new_n37_));
  INV        g27(.A(i2), .Y(new_n38_));
  NA3        g28(.A(new_n30_), .B(new_n15_), .C(i0), .Y(new_n39_));
  NAi21      g29(.An(i4), .B(i5), .Y(new_n40_));
  OAI210     g30(.A0(new_n40_), .A1(new_n38_), .B0(new_n39_), .Y(new_n41_));
  OAI210     g31(.A0(new_n41_), .A1(new_n37_), .B0(i1), .Y(new_n42_));
  OAI210     g32(.A0(i7), .A1(i6), .B0(i4), .Y(new_n43_));
  NO2        g33(.A(i4), .B(i2), .Y(new_n44_));
  NA2        g34(.A(i5), .B(i3), .Y(new_n45_));
  NO2        g35(.A(new_n45_), .B(new_n44_), .Y(new_n46_));
  NA2        g36(.A(new_n46_), .B(new_n43_), .Y(new_n47_));
  AO210      g37(.A0(new_n47_), .A1(new_n42_), .B0(i8), .Y(new_n48_));
  NAi32      g38(.An(i3), .Bn(i1), .C(i5), .Y(new_n49_));
  AOI210     g39(.A0(new_n49_), .A1(new_n26_), .B0(i0), .Y(new_n50_));
  NO3        g40(.A(i7), .B(new_n35_), .C(i1), .Y(new_n51_));
  OAI210     g41(.A0(new_n51_), .A1(new_n50_), .B0(i2), .Y(new_n52_));
  NO3        g42(.A(i8), .B(i7), .C(i0), .Y(new_n53_));
  OAI210     g43(.A0(new_n53_), .A1(new_n44_), .B0(i1), .Y(new_n54_));
  INV        g44(.A(i8), .Y(new_n55_));
  INV        g45(.A(i0), .Y(new_n56_));
  NAi21      g46(.An(i7), .B(i5), .Y(new_n57_));
  OAI220     g47(.A0(new_n57_), .A1(new_n34_), .B0(i4), .B1(new_n56_), .Y(new_n58_));
  OAI220     g48(.A0(i8), .A1(i5), .B0(i7), .B1(i3), .Y(new_n59_));
  AOI220     g49(.A0(new_n59_), .A1(i4), .B0(new_n58_), .B1(new_n55_), .Y(new_n60_));
  NA3        g50(.A(new_n60_), .B(new_n54_), .C(new_n52_), .Y(new_n61_));
  NA2        g51(.A(new_n61_), .B(i6), .Y(new_n62_));
  INV        g52(.A(i7), .Y(new_n63_));
  NA4        g53(.A(new_n55_), .B(new_n35_), .C(i1), .D(i0), .Y(new_n64_));
  AO210      g54(.A0(new_n64_), .A1(new_n40_), .B0(i2), .Y(new_n65_));
  OAI220     g55(.A0(i8), .A1(i1), .B0(i6), .B1(i3), .Y(new_n66_));
  NA2        g56(.A(new_n40_), .B(new_n34_), .Y(new_n67_));
  AOI210     g57(.A0(i5), .A1(i3), .B0(i1), .Y(new_n68_));
  AOI220     g58(.A0(new_n68_), .A1(new_n67_), .B0(new_n66_), .B1(i4), .Y(new_n69_));
  AOI210     g59(.A0(new_n69_), .A1(new_n65_), .B0(new_n63_), .Y(new_n70_));
  NA3        g60(.A(new_n13_), .B(i3), .C(i1), .Y(new_n71_));
  NA2        g61(.A(i4), .B(i2), .Y(new_n72_));
  OAI210     g62(.A0(new_n72_), .A1(i6), .B0(new_n71_), .Y(new_n73_));
  NA2        g63(.A(new_n35_), .B(i1), .Y(new_n74_));
  AOI210     g64(.A0(new_n63_), .A1(new_n38_), .B0(new_n45_), .Y(new_n75_));
  NA2        g65(.A(new_n27_), .B(new_n15_), .Y(new_n76_));
  OAI220     g66(.A0(new_n76_), .A1(new_n75_), .B0(new_n74_), .B1(new_n57_), .Y(new_n77_));
  MUX2       g67(.S(new_n56_), .A(new_n77_), .B(new_n73_), .Y(new_n78_));
  NO2        g68(.A(new_n78_), .B(new_n70_), .Y(new_n79_));
  NA4        g69(.A(new_n79_), .B(new_n62_), .C(new_n48_), .D(new_n33_), .Y(zori0));
endmodule


