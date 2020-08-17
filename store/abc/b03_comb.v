//Converted to Combinational , Module name: b03 , Timestamp: 2018-11-27T15:23:08.697018 
module b03 ( REQUEST1, REQUEST2, REQUEST3, REQUEST4, STATO_REG_0_, CODA0_REG_2_, CODA0_REG_1_, CODA0_REG_0_, CODA1_REG_2_, CODA1_REG_1_, CODA1_REG_0_, CODA2_REG_2_, CODA2_REG_1_, CODA2_REG_0_, CODA3_REG_2_, CODA3_REG_1_, CODA3_REG_0_, GRANT_REG_3_, GRANT_REG_2_, GRANT_REG_1_, GRANT_REG_0_, RU3_REG, FU1_REG, FU3_REG, RU1_REG, RU4_REG, FU2_REG, FU4_REG, RU2_REG, STATO_REG_1_, GRANT_O_REG_3_, GRANT_O_REG_2_, GRANT_O_REG_1_, GRANT_O_REG_0_, n17, n22, n27, n32, n37, n42, n47, n52, n57, n62, n67, n72, n77, n82, n87, n92, n97, n118, n123, n128, n133, n138, n143, n148, n153, n157 );
input REQUEST1, REQUEST2, REQUEST3, REQUEST4, STATO_REG_0_, CODA0_REG_2_, CODA0_REG_1_, CODA0_REG_0_, CODA1_REG_2_, CODA1_REG_1_, CODA1_REG_0_, CODA2_REG_2_, CODA2_REG_1_, CODA2_REG_0_, CODA3_REG_2_, CODA3_REG_1_, CODA3_REG_0_, GRANT_REG_3_, GRANT_REG_2_, GRANT_REG_1_, GRANT_REG_0_, RU3_REG, FU1_REG, FU3_REG, RU1_REG, RU4_REG, FU2_REG, FU4_REG, RU2_REG, STATO_REG_1_;
output GRANT_O_REG_3_, GRANT_O_REG_2_, GRANT_O_REG_1_, GRANT_O_REG_0_, n17, n22, n27, n32, n37, n42, n47, n52, n57, n62, n67, n72, n77, n82, n87, n92, n97, n118, n123, n128, n133, n138, n143, n148, n153, n157;
wire n99, n100, n101, n102_1, n103, n104, n105, n106_1, n107, n108, n109, n110_1, n111, n112, n113, n114_1, n115, n116, n117, n118_1, n119, n121, n122, n123_1, n124, n125, n127, n128_1, n129, n130, n132, n133_1, n134, n135, n137, n138_1, n139, n140, n142, n143_1, n144, n145, n147, n148_1, n149, n150, n152, n153_1, n154, n155, n157_1, n158, n159, n160, n162, n163, n165, n167, n169, n171, n173, n175, GRANT_O_REG_3__1, GRANT_O_REG_2__1, GRANT_O_REG_1__1, GRANT_O_REG_0__1;
INVX1    g00(.A(STATO_REG_0_), .Y(n17));
INVX1    g01(.A(RU4_REG), .Y(n99));
NOR4X1   g02(.A(FU4_REG), .B(n99), .C(RU3_REG), .D(RU2_REG), .Y(n100));
INVX1    g03(.A(RU3_REG), .Y(n101));
NOR3X1   g04(.A(RU2_REG), .B(FU3_REG), .C(n101), .Y(GRANT_O_REG_3__1));
INVX1    g05(.A(RU2_REG), .Y(n103));
NOR2X1   g06(.A(n103), .B(FU2_REG), .Y(n104));
NOR3X1   g07(.A(n104), .B(GRANT_O_REG_3__1), .C(n100), .Y(n105));
INVX1    g08(.A(RU1_REG), .Y(GRANT_O_REG_2__1));
NAND2X1  g09(.A(GRANT_O_REG_2__1), .B(STATO_REG_0_), .Y(n107));
INVX1    g10(.A(FU1_REG), .Y(n108));
AND2X1   g11(.A(RU1_REG), .B(STATO_REG_0_), .Y(n109));
INVX1    g12(.A(STATO_REG_1_), .Y(GRANT_O_REG_1__1));
NOR4X1   g13(.A(FU2_REG), .B(FU3_REG), .C(FU1_REG), .D(FU4_REG), .Y(n111));
NOR2X1   g14(.A(n111), .B(GRANT_O_REG_1__1), .Y(n112));
AOI21X1  g15(.A0(n109), .A1(n108), .B0(n112), .Y(n113));
OAI21X1  g16(.A0(n107), .A1(n105), .B0(n113), .Y(GRANT_O_REG_0__1));
NAND3X1  g17(.A(GRANT_O_REG_0__1), .B(STATO_REG_1_), .C(CODA1_REG_2_), .Y(n115));
INVX1    g18(.A(CODA0_REG_2_), .Y(n116));
OR2X1    g19(.A(GRANT_O_REG_0__1), .B(n116), .Y(n117));
OAI21X1  g20(.A0(RU2_REG), .A1(RU3_REG), .B0(GRANT_O_REG_2__1), .Y(n118_1));
NAND3X1  g21(.A(n118_1), .B(GRANT_O_REG_0__1), .C(GRANT_O_REG_1__1), .Y(n119));
NAND3X1  g22(.A(n119), .B(n117), .C(n115), .Y(n22));
NAND3X1  g23(.A(GRANT_O_REG_0__1), .B(STATO_REG_1_), .C(CODA1_REG_1_), .Y(n121));
INVX1    g24(.A(CODA0_REG_1_), .Y(n122));
OR2X1    g25(.A(GRANT_O_REG_0__1), .B(n122), .Y(n123_1));
AOI21X1  g26(.A0(n103), .A1(RU3_REG), .B0(RU1_REG), .Y(n124));
NAND3X1  g27(.A(n124), .B(GRANT_O_REG_0__1), .C(GRANT_O_REG_1__1), .Y(n125));
NAND3X1  g28(.A(n125), .B(n123_1), .C(n121), .Y(n27));
NAND3X1  g29(.A(GRANT_O_REG_0__1), .B(STATO_REG_1_), .C(CODA1_REG_0_), .Y(n127));
INVX1    g30(.A(CODA0_REG_0_), .Y(n128_1));
OR2X1    g31(.A(GRANT_O_REG_0__1), .B(n128_1), .Y(n129));
NAND4X1  g32(.A(GRANT_O_REG_1__1), .B(n103), .C(GRANT_O_REG_2__1), .D(GRANT_O_REG_0__1), .Y(n130));
NAND3X1  g33(.A(n130), .B(n129), .C(n127), .Y(n32));
NAND3X1  g34(.A(GRANT_O_REG_0__1), .B(GRANT_O_REG_1__1), .C(CODA0_REG_2_), .Y(n132));
INVX1    g35(.A(CODA1_REG_2_), .Y(n133_1));
OR2X1    g36(.A(GRANT_O_REG_0__1), .B(n133_1), .Y(n134));
NAND3X1  g37(.A(GRANT_O_REG_0__1), .B(STATO_REG_1_), .C(CODA2_REG_2_), .Y(n135));
NAND3X1  g38(.A(n135), .B(n134), .C(n132), .Y(n37));
NAND3X1  g39(.A(GRANT_O_REG_0__1), .B(GRANT_O_REG_1__1), .C(CODA0_REG_1_), .Y(n137));
INVX1    g40(.A(CODA1_REG_1_), .Y(n138_1));
OR2X1    g41(.A(GRANT_O_REG_0__1), .B(n138_1), .Y(n139));
NAND3X1  g42(.A(GRANT_O_REG_0__1), .B(STATO_REG_1_), .C(CODA2_REG_1_), .Y(n140));
NAND3X1  g43(.A(n140), .B(n139), .C(n137), .Y(n42));
NAND3X1  g44(.A(GRANT_O_REG_0__1), .B(GRANT_O_REG_1__1), .C(CODA0_REG_0_), .Y(n142));
INVX1    g45(.A(CODA1_REG_0_), .Y(n143_1));
OR2X1    g46(.A(GRANT_O_REG_0__1), .B(n143_1), .Y(n144));
NAND3X1  g47(.A(GRANT_O_REG_0__1), .B(STATO_REG_1_), .C(CODA2_REG_0_), .Y(n145));
NAND3X1  g48(.A(n145), .B(n144), .C(n142), .Y(n47));
NAND3X1  g49(.A(GRANT_O_REG_0__1), .B(GRANT_O_REG_1__1), .C(CODA1_REG_2_), .Y(n147));
INVX1    g50(.A(CODA2_REG_2_), .Y(n148_1));
OR2X1    g51(.A(GRANT_O_REG_0__1), .B(n148_1), .Y(n149));
NAND3X1  g52(.A(GRANT_O_REG_0__1), .B(STATO_REG_1_), .C(CODA3_REG_2_), .Y(n150));
NAND3X1  g53(.A(n150), .B(n149), .C(n147), .Y(n52));
NAND3X1  g54(.A(GRANT_O_REG_0__1), .B(GRANT_O_REG_1__1), .C(CODA1_REG_1_), .Y(n152));
INVX1    g55(.A(CODA2_REG_1_), .Y(n153_1));
OR2X1    g56(.A(GRANT_O_REG_0__1), .B(n153_1), .Y(n154));
NAND3X1  g57(.A(GRANT_O_REG_0__1), .B(STATO_REG_1_), .C(CODA3_REG_1_), .Y(n155));
NAND3X1  g58(.A(n155), .B(n154), .C(n152), .Y(n57));
NAND3X1  g59(.A(GRANT_O_REG_0__1), .B(GRANT_O_REG_1__1), .C(CODA1_REG_0_), .Y(n157_1));
INVX1    g60(.A(CODA2_REG_0_), .Y(n158));
OR2X1    g61(.A(GRANT_O_REG_0__1), .B(n158), .Y(n159));
NAND3X1  g62(.A(GRANT_O_REG_0__1), .B(STATO_REG_1_), .C(CODA3_REG_0_), .Y(n160));
NAND3X1  g63(.A(n160), .B(n159), .C(n157_1), .Y(n62));
INVX1    g64(.A(CODA3_REG_2_), .Y(n162));
NAND2X1  g65(.A(GRANT_O_REG_0__1), .B(GRANT_O_REG_1__1), .Y(n163));
OAI22X1  g66(.A0(GRANT_O_REG_0__1), .A1(n162), .B0(n148_1), .B1(n163), .Y(n67));
INVX1    g67(.A(CODA3_REG_1_), .Y(n165));
OAI22X1  g68(.A0(GRANT_O_REG_0__1), .A1(n165), .B0(n153_1), .B1(n163), .Y(n72));
INVX1    g69(.A(CODA3_REG_0_), .Y(n167));
OAI22X1  g70(.A0(GRANT_O_REG_0__1), .A1(n167), .B0(n158), .B1(n163), .Y(n77));
NOR3X1   g71(.A(CODA0_REG_0_), .B(CODA0_REG_1_), .C(n116), .Y(n169));
MX2X1    g72(.A(GRANT_REG_3_), .B(n169), .S0(n112), .Y(n82));
NOR3X1   g73(.A(CODA0_REG_0_), .B(n122), .C(CODA0_REG_2_), .Y(n171));
MX2X1    g74(.A(GRANT_REG_2_), .B(n171), .S0(n112), .Y(n87));
NOR3X1   g75(.A(n128_1), .B(CODA0_REG_1_), .C(CODA0_REG_2_), .Y(n173));
MX2X1    g76(.A(GRANT_REG_1_), .B(n173), .S0(n112), .Y(n92));
NOR3X1   g77(.A(n128_1), .B(n122), .C(n116), .Y(n175));
MX2X1    g78(.A(GRANT_REG_0_), .B(n175), .S0(n112), .Y(n97));
MX2X1    g79(.A(GRANT_O_REG_3_), .B(GRANT_REG_3_), .S0(STATO_REG_0_), .Y(GRANT_O_REG_3_));
MX2X1    g80(.A(GRANT_O_REG_2_), .B(GRANT_REG_2_), .S0(STATO_REG_0_), .Y(GRANT_O_REG_2_));
MX2X1    g81(.A(GRANT_O_REG_1_), .B(GRANT_REG_1_), .S0(STATO_REG_0_), .Y(GRANT_O_REG_1_));
MX2X1    g82(.A(GRANT_O_REG_0_), .B(GRANT_REG_0_), .S0(STATO_REG_0_), .Y(GRANT_O_REG_0_));
MX2X1    g83(.A(REQUEST3), .B(RU3_REG), .S0(STATO_REG_0_), .Y(n118));
MX2X1    g84(.A(FU1_REG), .B(RU1_REG), .S0(STATO_REG_0_), .Y(n123));
MX2X1    g85(.A(FU3_REG), .B(RU3_REG), .S0(STATO_REG_0_), .Y(n128));
MX2X1    g86(.A(REQUEST1), .B(RU1_REG), .S0(STATO_REG_0_), .Y(n133));
MX2X1    g87(.A(REQUEST4), .B(RU4_REG), .S0(STATO_REG_0_), .Y(n138));
MX2X1    g88(.A(FU2_REG), .B(RU2_REG), .S0(STATO_REG_0_), .Y(n143));
MX2X1    g89(.A(FU4_REG), .B(RU4_REG), .S0(STATO_REG_0_), .Y(n148));
MX2X1    g90(.A(REQUEST2), .B(RU2_REG), .S0(STATO_REG_0_), .Y(n153));
BUFX1    g91(.A(STATO_REG_0_), .Y(n157));
endmodule