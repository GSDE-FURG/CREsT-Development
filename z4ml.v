// Benchmark "z4ml" written by ABC on Thu Nov  9 16:56:44 2023

module z4ml ( 
    \1 , \2 , \3 , \4 , \5 , \6 , \7 ,
    \24 , \25 , \26 , \27   );
  input  \1 , \2 , \3 , \4 , \5 , \6 , \7 ;
  output \24 , \25 , \26 , \27 ;
  wire new_n12_, new_n13_, new_n14_, new_n15_, new_n16_, new_n17_, new_n18_,
    new_n19_, new_n20_, new_n21_, new_n23_, new_n24_, new_n25_, new_n26_,
    new_n27_, new_n28_, new_n29_, new_n30_, new_n32_, new_n33_, new_n34_,
    new_n36_;
  NOR2xp33_ASAP7_75t_R      g00(.A(\2 ), .B(\5 ), .Y(new_n12_));
  MAJIxp5_ASAP7_75t_R       g01(.A(\4 ), .B(\7 ), .C(\1 ), .Y(new_n13_));
  NOR2xp33_ASAP7_75t_R      g02(.A(\3 ), .B(\6 ), .Y(new_n14_));
  NOR2xp33_ASAP7_75t_R      g03(.A(new_n14_), .B(new_n13_), .Y(new_n15_));
  INVx1_ASAP7_75t_R         g04(.A(new_n15_), .Y(new_n16_));
  INVx1_ASAP7_75t_R         g05(.A(\3 ), .Y(new_n17_));
  INVx1_ASAP7_75t_R         g06(.A(\6 ), .Y(new_n18_));
  NOR2xp33_ASAP7_75t_R      g07(.A(new_n17_), .B(new_n18_), .Y(new_n19_));
  INVx1_ASAP7_75t_R         g08(.A(new_n19_), .Y(new_n20_));
  NAND2xp33_ASAP7_75t_R     g09(.A(\5 ), .B(\2 ), .Y(new_n21_));
  AOI31xp33_ASAP7_75t_R     g10(.A1(new_n21_), .A2(new_n16_), .A3(new_n20_), .B(new_n12_), .Y(\24 ));
  INVx1_ASAP7_75t_R         g11(.A(new_n21_), .Y(new_n23_));
  NOR2xp33_ASAP7_75t_R      g12(.A(new_n12_), .B(new_n23_), .Y(new_n24_));
  INVx1_ASAP7_75t_R         g13(.A(new_n24_), .Y(new_n25_));
  NAND2xp33_ASAP7_75t_R     g14(.A(new_n15_), .B(new_n25_), .Y(new_n26_));
  MAJIxp5_ASAP7_75t_R       g15(.A(\4 ), .B(\7 ), .C(\1 ), .Y(new_n27_));
  NAND3xp33_ASAP7_75t_R     g16(.A(new_n24_), .B(new_n27_), .C(new_n20_), .Y(new_n28_));
  NAND2xp33_ASAP7_75t_R     g17(.A(new_n14_), .B(new_n24_), .Y(new_n29_));
  NAND2xp33_ASAP7_75t_R     g18(.A(new_n19_), .B(new_n25_), .Y(new_n30_));
  NAND4xp25_ASAP7_75t_R     g19(.A(new_n30_), .B(new_n26_), .C(new_n28_), .D(new_n29_), .Y(\25 ));
  INVx1_ASAP7_75t_R         g20(.A(new_n27_), .Y(new_n32_));
  INVx1_ASAP7_75t_R         g21(.A(new_n13_), .Y(new_n33_));
  OAI21xp33_ASAP7_75t_R     g22(.A1(new_n19_), .A2(new_n14_), .B(new_n33_), .Y(new_n34_));
  OAI31xp33_ASAP7_75t_R     g23(.A1(new_n32_), .A2(new_n19_), .A3(new_n14_), .B(new_n34_), .Y(\26 ));
  XOR2xp5_ASAP7_75t_R       g24(.A(\4 ), .B(\7 ), .Y(new_n36_));
  XOR2xp5_ASAP7_75t_R       g25(.A(\1 ), .B(new_n36_), .Y(\27 ));
endmodule


