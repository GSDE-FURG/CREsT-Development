// Benchmark "9sym/9sym_1_20_0_esp" written by ABC on Wed Mar  8 02:20:43 2023

module \9sym/9sym_1_20_0_esp  ( 
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
    new_n88_, new_n89_, new_n90_, new_n91_, new_n92_, new_n93_, new_n94_,
    new_n95_;
  NOi21      g00(.An(i8), .B(i7), .Y(new_n11_));
  AN2        g01(.A(i6), .B(i5), .Y(new_n12_));
  AN2        g02(.A(new_n12_), .B(new_n11_), .Y(new_n13_));
  NOi21      g03(.An(i4), .B(i8), .Y(new_n14_));
  AO210      g04(.A0(new_n14_), .A1(i7), .B0(i2), .Y(new_n15_));
  INV        g05(.A(i7), .Y(new_n16_));
  NA2        g06(.A(i8), .B(new_n16_), .Y(new_n17_));
  INV        g07(.A(i3), .Y(new_n18_));
  NA3        g08(.A(i5), .B(new_n18_), .C(i1), .Y(new_n19_));
  AOI210     g09(.A0(new_n19_), .A1(new_n17_), .B0(i4), .Y(new_n20_));
  NAi21      g10(.An(i6), .B(i4), .Y(new_n21_));
  NA2        g11(.A(new_n21_), .B(i2), .Y(new_n22_));
  OAI220     g12(.A0(new_n22_), .A1(new_n20_), .B0(new_n15_), .B1(new_n13_), .Y(new_n23_));
  INV        g13(.A(i5), .Y(new_n24_));
  INV        g14(.A(i1), .Y(new_n25_));
  INV        g15(.A(i2), .Y(new_n26_));
  NA2        g16(.A(i7), .B(i6), .Y(new_n27_));
  OAI220     g17(.A0(new_n27_), .A1(new_n26_), .B0(new_n18_), .B1(new_n25_), .Y(new_n28_));
  NOi21      g18(.An(i5), .B(i8), .Y(new_n29_));
  NOi21      g19(.An(i8), .B(i2), .Y(new_n30_));
  NOi21      g20(.An(i4), .B(i3), .Y(new_n31_));
  OAI210     g21(.A0(new_n30_), .A1(new_n29_), .B0(new_n31_), .Y(new_n32_));
  NOi21      g22(.An(i5), .B(i6), .Y(new_n33_));
  NA2        g23(.A(new_n33_), .B(i8), .Y(new_n34_));
  INV        g24(.A(i8), .Y(new_n35_));
  NA3        g25(.A(new_n35_), .B(new_n16_), .C(i6), .Y(new_n36_));
  NA4        g26(.A(new_n36_), .B(new_n34_), .C(new_n32_), .D(i1), .Y(new_n37_));
  INV        g27(.A(i4), .Y(new_n38_));
  AOI210     g28(.A0(i7), .A1(new_n38_), .B0(new_n18_), .Y(new_n39_));
  OAI210     g29(.A0(new_n12_), .A1(i3), .B0(i2), .Y(new_n40_));
  AN2        g30(.A(i5), .B(i3), .Y(new_n41_));
  AOI210     g31(.A0(new_n41_), .A1(new_n30_), .B0(i1), .Y(new_n42_));
  OAI210     g32(.A0(new_n40_), .A1(new_n39_), .B0(new_n42_), .Y(new_n43_));
  AOI220     g33(.A0(new_n43_), .A1(new_n37_), .B0(new_n28_), .B1(new_n24_), .Y(new_n44_));
  AOI210     g34(.A0(new_n44_), .A1(new_n23_), .B0(i0), .Y(new_n45_));
  INV        g35(.A(i0), .Y(new_n46_));
  NO2        g36(.A(i7), .B(i2), .Y(new_n47_));
  AOI220     g37(.A0(new_n47_), .A1(new_n33_), .B0(new_n14_), .B1(new_n24_), .Y(new_n48_));
  NA3        g38(.A(new_n29_), .B(i4), .C(new_n25_), .Y(new_n49_));
  OAI210     g39(.A0(new_n48_), .A1(new_n18_), .B0(new_n49_), .Y(new_n50_));
  NO2        g40(.A(i7), .B(i6), .Y(new_n51_));
  AOI220     g41(.A0(new_n51_), .A1(new_n38_), .B0(new_n35_), .B1(new_n24_), .Y(new_n52_));
  NOi21      g42(.An(i3), .B(i1), .Y(new_n53_));
  NOi21      g43(.An(i7), .B(i6), .Y(new_n54_));
  AOI220     g44(.A0(new_n54_), .A1(new_n53_), .B0(new_n35_), .B1(i6), .Y(new_n55_));
  OAI220     g45(.A0(new_n55_), .A1(i4), .B0(new_n52_), .B1(new_n26_), .Y(new_n56_));
  NO2        g46(.A(new_n56_), .B(new_n50_), .Y(new_n57_));
  NO2        g47(.A(new_n24_), .B(i3), .Y(new_n58_));
  OAI210     g48(.A0(new_n16_), .A1(i3), .B0(new_n21_), .Y(new_n59_));
  NO2        g49(.A(i8), .B(i2), .Y(new_n60_));
  AOI220     g50(.A0(new_n60_), .A1(new_n59_), .B0(new_n58_), .B1(new_n16_), .Y(new_n61_));
  INV        g51(.A(i6), .Y(new_n62_));
  OAI210     g52(.A0(i5), .A1(i3), .B0(i7), .Y(new_n63_));
  AOI220     g53(.A0(new_n63_), .A1(new_n25_), .B0(new_n62_), .B1(new_n24_), .Y(new_n64_));
  OA220      g54(.A0(new_n64_), .A1(new_n35_), .B0(new_n61_), .B1(new_n25_), .Y(new_n65_));
  AOI210     g55(.A0(new_n65_), .A1(new_n57_), .B0(new_n46_), .Y(new_n66_));
  NOi21      g56(.An(i7), .B(i1), .Y(new_n67_));
  AOI210     g57(.A0(new_n16_), .A1(i4), .B0(new_n67_), .Y(new_n68_));
  NO3        g58(.A(new_n68_), .B(i5), .C(new_n18_), .Y(new_n69_));
  NO2        g59(.A(new_n21_), .B(new_n24_), .Y(new_n70_));
  NO2        g60(.A(new_n27_), .B(i3), .Y(new_n71_));
  OA210      g61(.A0(new_n71_), .A1(new_n70_), .B0(new_n25_), .Y(new_n72_));
  OAI210     g62(.A0(new_n72_), .A1(new_n69_), .B0(i8), .Y(new_n73_));
  NA3        g63(.A(i8), .B(new_n24_), .C(new_n18_), .Y(new_n74_));
  OAI220     g64(.A0(new_n74_), .A1(new_n16_), .B0(new_n62_), .B1(i4), .Y(new_n75_));
  OAI220     g65(.A0(new_n36_), .A1(new_n18_), .B0(new_n16_), .B1(i4), .Y(new_n76_));
  AOI220     g66(.A0(new_n76_), .A1(i5), .B0(new_n75_), .B1(i1), .Y(new_n77_));
  AOI210     g67(.A0(new_n77_), .A1(new_n73_), .B0(i2), .Y(new_n78_));
  NA2        g68(.A(new_n41_), .B(new_n35_), .Y(new_n79_));
  AOI210     g69(.A0(new_n79_), .A1(new_n74_), .B0(new_n26_), .Y(new_n80_));
  NA2        g70(.A(i5), .B(new_n18_), .Y(new_n81_));
  NA2        g71(.A(i7), .B(new_n25_), .Y(new_n82_));
  NO2        g72(.A(new_n67_), .B(new_n11_), .Y(new_n83_));
  NA3        g73(.A(i6), .B(new_n24_), .C(i3), .Y(new_n84_));
  OAI220     g74(.A0(new_n84_), .A1(new_n83_), .B0(new_n82_), .B1(new_n81_), .Y(new_n85_));
  OAI210     g75(.A0(new_n85_), .A1(new_n80_), .B0(new_n38_), .Y(new_n86_));
  AOI220     g76(.A0(new_n51_), .A1(new_n41_), .B0(i6), .B1(new_n25_), .Y(new_n87_));
  NA2        g77(.A(new_n27_), .B(new_n18_), .Y(new_n88_));
  OAI220     g78(.A0(new_n88_), .A1(new_n51_), .B0(new_n87_), .B1(i8), .Y(new_n89_));
  NA2        g79(.A(new_n89_), .B(i4), .Y(new_n90_));
  NO3        g80(.A(i8), .B(i4), .C(new_n18_), .Y(new_n91_));
  NO4        g81(.A(new_n16_), .B(i6), .C(i3), .D(new_n26_), .Y(new_n92_));
  OAI210     g82(.A0(new_n92_), .A1(new_n91_), .B0(i1), .Y(new_n93_));
  NA4        g83(.A(new_n53_), .B(new_n16_), .C(i6), .D(i2), .Y(new_n94_));
  NA4        g84(.A(new_n94_), .B(new_n93_), .C(new_n90_), .D(new_n86_), .Y(new_n95_));
  OR4        g85(.A(new_n95_), .B(new_n78_), .C(new_n66_), .D(new_n45_), .Y(zori0));
endmodule


