// Benchmark "9sym/9sym_1_28_0_esp" written by ABC on Wed Mar  8 02:22:42 2023

module \9sym/9sym_1_28_0_esp  ( 
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
    new_n81_, new_n82_, new_n83_, new_n84_, new_n85_;
  INV        g00(.A(i0), .Y(new_n11_));
  INV        g01(.A(i2), .Y(new_n12_));
  INV        g02(.A(i5), .Y(new_n13_));
  NO3        g03(.A(new_n13_), .B(i4), .C(i3), .Y(new_n14_));
  INV        g04(.A(i7), .Y(new_n15_));
  NO2        g05(.A(new_n15_), .B(i6), .Y(new_n16_));
  OAI210     g06(.A0(new_n16_), .A1(new_n14_), .B0(i1), .Y(new_n17_));
  INV        g07(.A(i6), .Y(new_n18_));
  NA2        g08(.A(new_n18_), .B(i4), .Y(new_n19_));
  NAi21      g09(.An(i5), .B(i7), .Y(new_n20_));
  OA210      g10(.A0(new_n20_), .A1(new_n18_), .B0(new_n19_), .Y(new_n21_));
  AOI210     g11(.A0(new_n21_), .A1(new_n17_), .B0(new_n12_), .Y(new_n22_));
  INV        g12(.A(i1), .Y(new_n23_));
  INV        g13(.A(i3), .Y(new_n24_));
  NO3        g14(.A(new_n15_), .B(i4), .C(new_n24_), .Y(new_n25_));
  NO4        g15(.A(new_n18_), .B(new_n13_), .C(i3), .D(new_n12_), .Y(new_n26_));
  OAI210     g16(.A0(new_n26_), .A1(new_n25_), .B0(new_n23_), .Y(new_n27_));
  INV        g17(.A(i8), .Y(new_n28_));
  INV        g18(.A(i4), .Y(new_n29_));
  NAi21      g19(.An(i7), .B(i6), .Y(new_n30_));
  NAi21      g20(.An(i3), .B(i5), .Y(new_n31_));
  OAI210     g21(.A0(new_n31_), .A1(new_n29_), .B0(new_n30_), .Y(new_n32_));
  OAI210     g22(.A0(i8), .A1(i2), .B0(i5), .Y(new_n33_));
  AOI220     g23(.A0(new_n33_), .A1(i3), .B0(new_n32_), .B1(new_n28_), .Y(new_n34_));
  OAI210     g24(.A0(new_n34_), .A1(new_n23_), .B0(new_n27_), .Y(new_n35_));
  OAI210     g25(.A0(new_n35_), .A1(new_n22_), .B0(new_n11_), .Y(new_n36_));
  NO2        g26(.A(i3), .B(i2), .Y(new_n37_));
  NOi21      g27(.An(i2), .B(i4), .Y(new_n38_));
  AOI220     g28(.A0(new_n38_), .A1(new_n15_), .B0(new_n37_), .B1(i4), .Y(new_n39_));
  NO2        g29(.A(i7), .B(i2), .Y(new_n40_));
  NA3        g30(.A(new_n40_), .B(new_n13_), .C(i4), .Y(new_n41_));
  OA210      g31(.A0(new_n39_), .A1(i0), .B0(new_n41_), .Y(new_n42_));
  NOi21      g32(.An(i7), .B(i5), .Y(new_n43_));
  NOi21      g33(.An(i5), .B(i0), .Y(new_n44_));
  AOI220     g34(.A0(new_n44_), .A1(new_n18_), .B0(new_n37_), .B1(new_n43_), .Y(new_n45_));
  NO2        g35(.A(i2), .B(i1), .Y(new_n46_));
  NOi41      g36(.An(i6), .B(i7), .C(i5), .D(i4), .Y(new_n47_));
  AOI210     g37(.A0(new_n46_), .A1(new_n44_), .B0(new_n47_), .Y(new_n48_));
  OA220      g38(.A0(new_n48_), .A1(new_n24_), .B0(new_n45_), .B1(new_n23_), .Y(new_n49_));
  NO2        g39(.A(i6), .B(i2), .Y(new_n50_));
  OA210      g40(.A0(new_n50_), .A1(new_n15_), .B0(new_n23_), .Y(new_n51_));
  NO3        g41(.A(i7), .B(i6), .C(i4), .Y(new_n52_));
  OAI210     g42(.A0(new_n52_), .A1(new_n51_), .B0(i0), .Y(new_n53_));
  NO3        g43(.A(new_n50_), .B(new_n40_), .C(i1), .Y(new_n54_));
  AOI210     g44(.A0(i5), .A1(i2), .B0(i3), .Y(new_n55_));
  OAI210     g45(.A0(new_n54_), .A1(new_n38_), .B0(new_n55_), .Y(new_n56_));
  NA4        g46(.A(new_n56_), .B(new_n53_), .C(new_n49_), .D(new_n42_), .Y(new_n57_));
  NA2        g47(.A(new_n57_), .B(i8), .Y(new_n58_));
  NA2        g48(.A(i5), .B(new_n29_), .Y(new_n59_));
  NA4        g49(.A(new_n28_), .B(new_n24_), .C(i1), .D(i0), .Y(new_n60_));
  AOI210     g50(.A0(new_n60_), .A1(new_n59_), .B0(i2), .Y(new_n61_));
  AOI210     g51(.A0(new_n28_), .A1(i4), .B0(new_n14_), .Y(new_n62_));
  OAI220     g52(.A0(new_n62_), .A1(i1), .B0(new_n19_), .B1(i3), .Y(new_n63_));
  OAI210     g53(.A0(new_n63_), .A1(new_n61_), .B0(i7), .Y(new_n64_));
  OA210      g54(.A0(new_n40_), .A1(new_n13_), .B0(i0), .Y(new_n65_));
  NO4        g55(.A(i8), .B(i7), .C(new_n13_), .D(new_n29_), .Y(new_n66_));
  OAI210     g56(.A0(new_n66_), .A1(new_n65_), .B0(new_n18_), .Y(new_n67_));
  NO2        g57(.A(i8), .B(new_n13_), .Y(new_n68_));
  NAi21      g58(.An(i4), .B(i2), .Y(new_n69_));
  OAI210     g59(.A0(new_n30_), .A1(i2), .B0(new_n69_), .Y(new_n70_));
  NA2        g60(.A(new_n30_), .B(i2), .Y(new_n71_));
  AOI210     g61(.A0(new_n20_), .A1(new_n12_), .B0(i1), .Y(new_n72_));
  AOI220     g62(.A0(new_n72_), .A1(new_n71_), .B0(new_n70_), .B1(new_n68_), .Y(new_n73_));
  AOI210     g63(.A0(new_n73_), .A1(new_n67_), .B0(new_n24_), .Y(new_n74_));
  NOi31      g64(.An(new_n50_), .B(i8), .C(new_n29_), .Y(new_n75_));
  NO2        g65(.A(new_n31_), .B(i7), .Y(new_n76_));
  OAI210     g66(.A0(new_n76_), .A1(new_n75_), .B0(i1), .Y(new_n77_));
  NO3        g67(.A(i6), .B(i3), .C(new_n12_), .Y(new_n78_));
  NO3        g68(.A(i8), .B(new_n18_), .C(i4), .Y(new_n79_));
  NO2        g69(.A(new_n79_), .B(new_n78_), .Y(new_n80_));
  AOI210     g70(.A0(new_n80_), .A1(new_n77_), .B0(new_n11_), .Y(new_n81_));
  AOI220     g71(.A0(new_n28_), .A1(new_n13_), .B0(new_n15_), .B1(new_n24_), .Y(new_n82_));
  NO3        g72(.A(new_n82_), .B(new_n18_), .C(new_n29_), .Y(new_n83_));
  NO4        g73(.A(new_n18_), .B(i4), .C(i2), .D(new_n23_), .Y(new_n84_));
  NO4        g74(.A(new_n84_), .B(new_n83_), .C(new_n81_), .D(new_n74_), .Y(new_n85_));
  NA4        g75(.A(new_n85_), .B(new_n64_), .C(new_n58_), .D(new_n36_), .Y(zori0));
endmodule


