// Benchmark "9sym/9sym_1_19_0_esp" written by ABC on Wed Mar  8 02:20:29 2023

module \9sym/9sym_1_19_0_esp  ( 
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
    new_n95_, new_n96_, new_n97_, new_n98_, new_n99_, new_n100_, new_n101_,
    new_n102_;
  INV        g00(.A(i6), .Y(new_n11_));
  INV        g01(.A(i4), .Y(new_n12_));
  NAi21      g02(.An(i1), .B(i5), .Y(new_n13_));
  NAi21      g03(.An(i5), .B(i3), .Y(new_n14_));
  AOI210     g04(.A0(new_n14_), .A1(new_n13_), .B0(new_n12_), .Y(new_n15_));
  NA2        g05(.A(i5), .B(i3), .Y(new_n16_));
  NO2        g06(.A(new_n16_), .B(i7), .Y(new_n17_));
  OAI210     g07(.A0(new_n17_), .A1(new_n15_), .B0(new_n11_), .Y(new_n18_));
  OAI210     g08(.A0(i8), .A1(i6), .B0(i4), .Y(new_n19_));
  INV        g09(.A(i1), .Y(new_n20_));
  INV        g10(.A(i5), .Y(new_n21_));
  AOI210     g11(.A0(new_n21_), .A1(new_n12_), .B0(new_n20_), .Y(new_n22_));
  NA2        g12(.A(new_n22_), .B(new_n19_), .Y(new_n23_));
  AOI210     g13(.A0(new_n23_), .A1(new_n18_), .B0(i2), .Y(new_n24_));
  NO2        g14(.A(i6), .B(i5), .Y(new_n25_));
  INV        g15(.A(i8), .Y(new_n26_));
  NO2        g16(.A(new_n26_), .B(i7), .Y(new_n27_));
  NA2        g17(.A(new_n27_), .B(new_n25_), .Y(new_n28_));
  INV        g18(.A(i3), .Y(new_n29_));
  NAi21      g19(.An(i6), .B(i7), .Y(new_n30_));
  NO3        g20(.A(new_n30_), .B(i4), .C(new_n29_), .Y(new_n31_));
  OAI210     g21(.A0(new_n31_), .A1(new_n27_), .B0(new_n20_), .Y(new_n32_));
  NO2        g22(.A(i8), .B(new_n11_), .Y(new_n33_));
  NAi21      g23(.An(i7), .B(i8), .Y(new_n34_));
  NO2        g24(.A(new_n34_), .B(i6), .Y(new_n35_));
  OAI210     g25(.A0(new_n35_), .A1(new_n33_), .B0(new_n12_), .Y(new_n36_));
  NAi21      g26(.An(i2), .B(i7), .Y(new_n37_));
  OAI210     g27(.A0(new_n37_), .A1(i8), .B0(i1), .Y(new_n38_));
  NAi21      g28(.An(i5), .B(i8), .Y(new_n39_));
  AOI210     g29(.A0(new_n39_), .A1(new_n20_), .B0(i3), .Y(new_n40_));
  INV        g30(.A(i7), .Y(new_n41_));
  AO220      g31(.A0(new_n25_), .A1(new_n20_), .B0(new_n41_), .B1(new_n29_), .Y(new_n42_));
  AOI220     g32(.A0(new_n42_), .A1(i2), .B0(new_n40_), .B1(new_n38_), .Y(new_n43_));
  NA4        g33(.A(new_n43_), .B(new_n36_), .C(new_n32_), .D(new_n28_), .Y(new_n44_));
  OAI210     g34(.A0(new_n44_), .A1(new_n24_), .B0(i0), .Y(new_n45_));
  INV        g35(.A(i0), .Y(new_n46_));
  NAi21      g36(.An(i3), .B(i2), .Y(new_n47_));
  NO2        g37(.A(new_n47_), .B(new_n13_), .Y(new_n48_));
  NO3        g38(.A(i8), .B(i7), .C(new_n20_), .Y(new_n49_));
  OAI210     g39(.A0(new_n49_), .A1(new_n48_), .B0(i6), .Y(new_n50_));
  INV        g40(.A(i2), .Y(new_n51_));
  AOI210     g41(.A0(i6), .A1(i4), .B0(new_n51_), .Y(new_n52_));
  OAI210     g42(.A0(new_n27_), .A1(i4), .B0(new_n52_), .Y(new_n53_));
  NA2        g43(.A(i7), .B(i5), .Y(new_n54_));
  NAi41      g44(.An(i3), .B(i8), .C(i4), .D(i1), .Y(new_n55_));
  OAI210     g45(.A0(new_n54_), .A1(i8), .B0(new_n55_), .Y(new_n56_));
  NA2        g46(.A(i6), .B(i2), .Y(new_n57_));
  OAI220     g47(.A0(new_n57_), .A1(new_n41_), .B0(new_n29_), .B1(new_n20_), .Y(new_n58_));
  AOI220     g48(.A0(new_n58_), .A1(new_n21_), .B0(new_n56_), .B1(new_n51_), .Y(new_n59_));
  NA3        g49(.A(new_n59_), .B(new_n53_), .C(new_n50_), .Y(new_n60_));
  NAi21      g50(.An(i4), .B(i2), .Y(new_n61_));
  OAI210     g51(.A0(i8), .A1(new_n12_), .B0(new_n61_), .Y(new_n62_));
  AOI220     g52(.A0(new_n62_), .A1(new_n29_), .B0(i8), .B1(new_n11_), .Y(new_n63_));
  NA2        g53(.A(i5), .B(i1), .Y(new_n64_));
  NAi31      g54(.An(i2), .B(i8), .C(i5), .Y(new_n65_));
  NAi31      g55(.An(i4), .B(i7), .C(i2), .Y(new_n66_));
  NA3        g56(.A(new_n66_), .B(new_n65_), .C(new_n20_), .Y(new_n67_));
  OAI210     g57(.A0(i7), .A1(i2), .B0(i1), .Y(new_n68_));
  NA3        g58(.A(new_n68_), .B(new_n67_), .C(i3), .Y(new_n69_));
  OAI210     g59(.A0(new_n64_), .A1(new_n63_), .B0(new_n69_), .Y(new_n70_));
  OAI210     g60(.A0(new_n70_), .A1(new_n60_), .B0(new_n46_), .Y(new_n71_));
  NA2        g61(.A(new_n34_), .B(i3), .Y(new_n72_));
  NO2        g62(.A(i6), .B(i3), .Y(new_n73_));
  NO2        g63(.A(new_n73_), .B(i2), .Y(new_n74_));
  AOI210     g64(.A0(new_n74_), .A1(new_n72_), .B0(new_n33_), .Y(new_n75_));
  OAI220     g65(.A0(i8), .A1(i1), .B0(i6), .B1(i3), .Y(new_n76_));
  OAI210     g66(.A0(i8), .A1(i6), .B0(i3), .Y(new_n77_));
  NO3        g67(.A(i8), .B(i6), .C(i3), .Y(new_n78_));
  NO3        g68(.A(new_n78_), .B(new_n25_), .C(i7), .Y(new_n79_));
  AOI220     g69(.A0(new_n79_), .A1(new_n77_), .B0(new_n76_), .B1(i7), .Y(new_n80_));
  OAI210     g70(.A0(new_n75_), .A1(i5), .B0(new_n80_), .Y(new_n81_));
  NA2        g71(.A(new_n81_), .B(i4), .Y(new_n82_));
  NO2        g72(.A(new_n21_), .B(i2), .Y(new_n83_));
  NO2        g73(.A(new_n14_), .B(i7), .Y(new_n84_));
  NO2        g74(.A(new_n26_), .B(new_n11_), .Y(new_n85_));
  OAI210     g75(.A0(new_n84_), .A1(new_n83_), .B0(new_n85_), .Y(new_n86_));
  OAI220     g76(.A0(new_n39_), .A1(new_n30_), .B0(new_n11_), .B1(i2), .Y(new_n87_));
  NA2        g77(.A(new_n87_), .B(i1), .Y(new_n88_));
  OAI210     g78(.A0(new_n11_), .A1(new_n29_), .B0(new_n21_), .Y(new_n89_));
  NA4        g79(.A(new_n89_), .B(new_n16_), .C(i7), .D(new_n20_), .Y(new_n90_));
  NAi21      g80(.An(i8), .B(i3), .Y(new_n91_));
  NA2        g81(.A(new_n91_), .B(new_n39_), .Y(new_n92_));
  NA3        g82(.A(new_n92_), .B(new_n14_), .C(i2), .Y(new_n93_));
  NA4        g83(.A(new_n93_), .B(new_n90_), .C(new_n88_), .D(new_n86_), .Y(new_n94_));
  NO3        g84(.A(new_n57_), .B(i7), .C(new_n29_), .Y(new_n95_));
  NOi41      g85(.An(new_n16_), .B(new_n73_), .C(new_n37_), .D(new_n26_), .Y(new_n96_));
  OAI210     g86(.A0(new_n96_), .A1(new_n95_), .B0(new_n20_), .Y(new_n97_));
  OAI220     g87(.A0(new_n91_), .A1(i5), .B0(new_n47_), .B1(new_n30_), .Y(new_n98_));
  NO3        g88(.A(i8), .B(new_n11_), .C(i2), .Y(new_n99_));
  AOI220     g89(.A0(new_n99_), .A1(new_n17_), .B0(new_n98_), .B1(i1), .Y(new_n100_));
  NA2        g90(.A(new_n100_), .B(new_n97_), .Y(new_n101_));
  AOI210     g91(.A0(new_n94_), .A1(new_n12_), .B0(new_n101_), .Y(new_n102_));
  NA4        g92(.A(new_n102_), .B(new_n82_), .C(new_n71_), .D(new_n45_), .Y(zori0));
endmodule


