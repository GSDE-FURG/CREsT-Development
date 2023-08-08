// Benchmark "9sym/9sym_1_26_0_esp" written by ABC on Wed Mar  8 02:22:16 2023

module \9sym/9sym_1_26_0_esp  ( 
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
    new_n74_, new_n75_, new_n76_, new_n77_, new_n78_, new_n79_, new_n80_,
    new_n81_, new_n82_, new_n83_, new_n84_, new_n85_, new_n86_, new_n87_,
    new_n88_;
  INV        g00(.A(i8), .Y(new_n11_));
  INV        g01(.A(i5), .Y(new_n12_));
  INV        g02(.A(i2), .Y(new_n13_));
  NO2        g03(.A(i4), .B(new_n13_), .Y(new_n14_));
  INV        g04(.A(i6), .Y(new_n15_));
  NO3        g05(.A(i7), .B(new_n15_), .C(i2), .Y(new_n16_));
  OAI210     g06(.A0(new_n16_), .A1(new_n14_), .B0(i3), .Y(new_n17_));
  AOI210     g07(.A0(i4), .A1(i0), .B0(i7), .Y(new_n18_));
  NOi31      g08(.An(i3), .B(i7), .C(i6), .Y(new_n19_));
  NOi31      g09(.An(i1), .B(i3), .C(i0), .Y(new_n20_));
  OAI210     g10(.A0(new_n20_), .A1(new_n19_), .B0(i4), .Y(new_n21_));
  OA210      g11(.A0(new_n18_), .A1(i1), .B0(new_n21_), .Y(new_n22_));
  AOI210     g12(.A0(new_n22_), .A1(new_n17_), .B0(new_n12_), .Y(new_n23_));
  INV        g13(.A(i0), .Y(new_n24_));
  NAi21      g14(.An(i5), .B(i4), .Y(new_n25_));
  OA210      g15(.A0(i4), .A1(new_n24_), .B0(new_n25_), .Y(new_n26_));
  INV        g16(.A(i3), .Y(new_n27_));
  AOI210     g17(.A0(new_n27_), .A1(new_n24_), .B0(i2), .Y(new_n28_));
  INV        g18(.A(i4), .Y(new_n29_));
  INV        g19(.A(i7), .Y(new_n30_));
  OAI220     g20(.A0(new_n30_), .A1(i3), .B0(i6), .B1(new_n29_), .Y(new_n31_));
  AOI210     g21(.A0(new_n31_), .A1(new_n28_), .B0(new_n24_), .Y(new_n32_));
  NO2        g22(.A(i7), .B(new_n15_), .Y(new_n33_));
  OAI210     g23(.A0(new_n28_), .A1(new_n33_), .B0(i1), .Y(new_n34_));
  OAI220     g24(.A0(new_n34_), .A1(new_n32_), .B0(new_n26_), .B1(new_n15_), .Y(new_n35_));
  OAI210     g25(.A0(new_n35_), .A1(new_n23_), .B0(new_n11_), .Y(new_n36_));
  NA2        g26(.A(new_n15_), .B(i4), .Y(new_n37_));
  NO2        g27(.A(new_n30_), .B(i6), .Y(new_n38_));
  NAi21      g28(.An(i3), .B(i5), .Y(new_n39_));
  NO2        g29(.A(new_n39_), .B(i4), .Y(new_n40_));
  OAI210     g30(.A0(new_n40_), .A1(new_n38_), .B0(i1), .Y(new_n41_));
  AOI210     g31(.A0(new_n41_), .A1(new_n37_), .B0(new_n13_), .Y(new_n42_));
  NAi21      g32(.An(i5), .B(i3), .Y(new_n43_));
  NOi21      g33(.An(new_n43_), .B(i2), .Y(new_n44_));
  NA2        g34(.A(i3), .B(i1), .Y(new_n45_));
  OA210      g35(.A0(i4), .A1(i3), .B0(new_n45_), .Y(new_n46_));
  NAi21      g36(.An(i4), .B(i2), .Y(new_n47_));
  NA2        g37(.A(i5), .B(i1), .Y(new_n48_));
  OAI220     g38(.A0(new_n48_), .A1(i6), .B0(new_n47_), .B1(i7), .Y(new_n49_));
  AOI210     g39(.A0(new_n46_), .A1(new_n44_), .B0(new_n49_), .Y(new_n50_));
  INV        g40(.A(i1), .Y(new_n51_));
  NA2        g41(.A(i6), .B(i2), .Y(new_n52_));
  OAI210     g42(.A0(new_n52_), .A1(new_n30_), .B0(new_n45_), .Y(new_n53_));
  NAi31      g43(.An(i4), .B(i7), .C(i3), .Y(new_n54_));
  OAI210     g44(.A0(new_n52_), .A1(new_n39_), .B0(new_n54_), .Y(new_n55_));
  AOI220     g45(.A0(new_n55_), .A1(new_n51_), .B0(new_n53_), .B1(new_n12_), .Y(new_n56_));
  OAI210     g46(.A0(new_n50_), .A1(new_n11_), .B0(new_n56_), .Y(new_n57_));
  OAI210     g47(.A0(new_n57_), .A1(new_n42_), .B0(new_n24_), .Y(new_n58_));
  NA3        g48(.A(i7), .B(new_n13_), .C(i1), .Y(new_n59_));
  OAI210     g49(.A0(new_n29_), .A1(new_n51_), .B0(i2), .Y(new_n60_));
  AOI210     g50(.A0(new_n60_), .A1(new_n59_), .B0(i5), .Y(new_n61_));
  NO4        g51(.A(new_n30_), .B(new_n15_), .C(i2), .D(i1), .Y(new_n62_));
  OAI210     g52(.A0(new_n62_), .A1(new_n61_), .B0(i8), .Y(new_n63_));
  OAI220     g53(.A0(new_n48_), .A1(i7), .B0(i6), .B1(new_n13_), .Y(new_n64_));
  NA2        g54(.A(i7), .B(i5), .Y(new_n65_));
  NO2        g55(.A(i4), .B(i1), .Y(new_n66_));
  NAi21      g56(.An(new_n65_), .B(new_n66_), .Y(new_n67_));
  XN2        g57(.A(i7), .B(i6), .Y(new_n68_));
  OAI210     g58(.A0(new_n68_), .A1(new_n29_), .B0(new_n67_), .Y(new_n69_));
  AOI210     g59(.A0(new_n64_), .A1(i0), .B0(new_n69_), .Y(new_n70_));
  AO210      g60(.A0(new_n70_), .A1(new_n63_), .B0(i3), .Y(new_n71_));
  NO2        g61(.A(new_n15_), .B(i4), .Y(new_n72_));
  OAI210     g62(.A0(new_n72_), .A1(new_n19_), .B0(i1), .Y(new_n73_));
  NAi21      g63(.An(i6), .B(i0), .Y(new_n74_));
  OAI220     g64(.A0(new_n74_), .A1(new_n11_), .B0(new_n43_), .B1(new_n30_), .Y(new_n75_));
  NAi21      g65(.An(i7), .B(i8), .Y(new_n76_));
  OAI220     g66(.A0(new_n76_), .A1(new_n25_), .B0(new_n65_), .B1(i4), .Y(new_n77_));
  AOI210     g67(.A0(new_n75_), .A1(new_n51_), .B0(new_n77_), .Y(new_n78_));
  AOI210     g68(.A0(new_n78_), .A1(new_n73_), .B0(i2), .Y(new_n79_));
  NA2        g69(.A(new_n30_), .B(i6), .Y(new_n80_));
  NO2        g70(.A(new_n66_), .B(new_n12_), .Y(new_n81_));
  NO2        g71(.A(i5), .B(i4), .Y(new_n82_));
  AOI220     g72(.A0(new_n82_), .A1(i8), .B0(i2), .B1(new_n51_), .Y(new_n83_));
  OAI220     g73(.A0(new_n83_), .A1(new_n80_), .B0(new_n81_), .B1(new_n74_), .Y(new_n84_));
  OAI210     g74(.A0(i6), .A1(i4), .B0(i1), .Y(new_n85_));
  NO2        g75(.A(new_n76_), .B(new_n24_), .Y(new_n86_));
  AO220      g76(.A0(new_n86_), .A1(new_n85_), .B0(new_n84_), .B1(i3), .Y(new_n87_));
  NO2        g77(.A(new_n87_), .B(new_n79_), .Y(new_n88_));
  NA4        g78(.A(new_n88_), .B(new_n71_), .C(new_n58_), .D(new_n36_), .Y(zori0));
endmodule


