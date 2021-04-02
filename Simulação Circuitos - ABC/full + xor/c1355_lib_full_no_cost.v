// Benchmark "c1355.blif" written by ABC on Wed Mar 31 18:38:48 2021

module \c1355.blif  ( 
    G1gat, G8gat, G15gat, G22gat, G29gat, G36gat, G43gat, G50gat, G57gat,
    G64gat, G71gat, G78gat, G85gat, G92gat, G99gat, G106gat, G113gat,
    G120gat, G127gat, G134gat, G141gat, G148gat, G155gat, G162gat, G169gat,
    G176gat, G183gat, G190gat, G197gat, G204gat, G211gat, G218gat, G225gat,
    G226gat, G227gat, G228gat, G229gat, G230gat, G231gat, G232gat, G233gat,
    G1324gat, G1325gat, G1326gat, G1327gat, G1328gat, G1329gat, G1330gat,
    G1331gat, G1332gat, G1333gat, G1334gat, G1335gat, G1336gat, G1337gat,
    G1338gat, G1339gat, G1340gat, G1341gat, G1342gat, G1343gat, G1344gat,
    G1345gat, G1346gat, G1347gat, G1348gat, G1349gat, G1350gat, G1351gat,
    G1352gat, G1353gat, G1354gat, G1355gat  );
  input  G1gat, G8gat, G15gat, G22gat, G29gat, G36gat, G43gat, G50gat,
    G57gat, G64gat, G71gat, G78gat, G85gat, G92gat, G99gat, G106gat,
    G113gat, G120gat, G127gat, G134gat, G141gat, G148gat, G155gat, G162gat,
    G169gat, G176gat, G183gat, G190gat, G197gat, G204gat, G211gat, G218gat,
    G225gat, G226gat, G227gat, G228gat, G229gat, G230gat, G231gat, G232gat,
    G233gat;
  output G1324gat, G1325gat, G1326gat, G1327gat, G1328gat, G1329gat, G1330gat,
    G1331gat, G1332gat, G1333gat, G1334gat, G1335gat, G1336gat, G1337gat,
    G1338gat, G1339gat, G1340gat, G1341gat, G1342gat, G1343gat, G1344gat,
    G1345gat, G1346gat, G1347gat, G1348gat, G1349gat, G1350gat, G1351gat,
    G1352gat, G1353gat, G1354gat, G1355gat;
  wire new_n74_, new_n75_, new_n76_, new_n77_, new_n78_, new_n79_, new_n80_,
    new_n81_, new_n82_, new_n83_, new_n84_, new_n85_, new_n86_, new_n87_,
    new_n88_, new_n89_, new_n90_, new_n91_, new_n92_, new_n93_, new_n94_,
    new_n95_, new_n96_, new_n97_, new_n98_, new_n99_, new_n100_, new_n101_,
    new_n102_, new_n103_, new_n104_, new_n105_, new_n106_, new_n107_,
    new_n108_, new_n109_, new_n110_, new_n111_, new_n112_, new_n113_,
    new_n114_, new_n115_, new_n116_, new_n117_, new_n118_, new_n119_,
    new_n120_, new_n121_, new_n122_, new_n123_, new_n124_, new_n125_,
    new_n126_, new_n127_, new_n128_, new_n129_, new_n130_, new_n131_,
    new_n132_, new_n133_, new_n134_, new_n135_, new_n136_, new_n137_,
    new_n138_, new_n139_, new_n140_, new_n141_, new_n142_, new_n143_,
    new_n144_, new_n145_, new_n146_, new_n147_, new_n148_, new_n149_,
    new_n150_, new_n151_, new_n152_, new_n153_, new_n154_, new_n155_,
    new_n156_, new_n157_, new_n158_, new_n159_, new_n160_, new_n161_,
    new_n162_, new_n163_, new_n164_, new_n165_, new_n166_, new_n167_,
    new_n168_, new_n169_, new_n170_, new_n171_, new_n173_, new_n175_,
    new_n177_, new_n179_, new_n180_, new_n181_, new_n183_, new_n185_,
    new_n187_, new_n189_, new_n190_, new_n191_, new_n192_, new_n193_,
    new_n195_, new_n197_, new_n199_, new_n201_, new_n202_, new_n204_,
    new_n206_, new_n208_, new_n210_, new_n211_, new_n212_, new_n213_,
    new_n214_, new_n215_, new_n216_, new_n217_, new_n218_, new_n219_,
    new_n221_, new_n223_, new_n225_, new_n227_, new_n228_, new_n230_,
    new_n232_, new_n234_, new_n236_, new_n237_, new_n239_, new_n241_,
    new_n243_, new_n245_, new_n246_, new_n248_, new_n250_, new_n252_;
  XOR2   g000(.A(G29gat), .B(G1gat), .Y(new_n74_));
  XOR2   g001(.A(G85gat), .B(G57gat), .Y(new_n75_));
  XOR2   g002(.A(new_n75_), .B(new_n74_), .Y(new_n76_));
  NAND2  g003(.A(G233gat), .B(G225gat), .Y(new_n77_));
  XOR2   g004(.A(G120gat), .B(G113gat), .Y(new_n78_));
  XOR2   g005(.A(G134gat), .B(G127gat), .Y(new_n79_));
  XOR2   g006(.A(new_n79_), .B(new_n78_), .Y(new_n80_));
  XOR2   g007(.A(G148gat), .B(G141gat), .Y(new_n81_));
  XOR2   g008(.A(G162gat), .B(G155gat), .Y(new_n82_));
  XOR2   g009(.A(new_n82_), .B(new_n81_), .Y(new_n83_));
  XOR2   g010(.A(new_n83_), .B(new_n80_), .Y(new_n84_));
  XOR2   g011(.A(new_n84_), .B(new_n77_), .Y(new_n85_));
  XOR2   g012(.A(new_n85_), .B(new_n76_), .Y(new_n86_));
  XOR2   g013(.A(G50gat), .B(G22gat), .Y(new_n87_));
  XOR2   g014(.A(G106gat), .B(G78gat), .Y(new_n88_));
  XOR2   g015(.A(new_n88_), .B(new_n87_), .Y(new_n89_));
  INV    g016(.A(new_n89_), .Y(new_n90_));
  NAND2  g017(.A(G233gat), .B(G228gat), .Y(new_n91_));
  XOR2   g018(.A(G204gat), .B(G197gat), .Y(new_n92_));
  XOR2   g019(.A(G218gat), .B(G211gat), .Y(new_n93_));
  XOR2   g020(.A(new_n93_), .B(new_n92_), .Y(new_n94_));
  XOR2   g021(.A(new_n94_), .B(new_n83_), .Y(new_n95_));
  XOR2   g022(.A(new_n95_), .B(new_n91_), .Y(new_n96_));
  XOR2   g023(.A(new_n96_), .B(new_n90_), .Y(new_n97_));
  XOR2   g024(.A(G43gat), .B(G15gat), .Y(new_n98_));
  XOR2   g025(.A(G99gat), .B(G71gat), .Y(new_n99_));
  XOR2   g026(.A(new_n99_), .B(new_n98_), .Y(new_n100_));
  INV    g027(.A(new_n100_), .Y(new_n101_));
  NAND2  g028(.A(G233gat), .B(G227gat), .Y(new_n102_));
  XOR2   g029(.A(G176gat), .B(G169gat), .Y(new_n103_));
  XOR2   g030(.A(G190gat), .B(G183gat), .Y(new_n104_));
  XOR2   g031(.A(new_n104_), .B(new_n103_), .Y(new_n105_));
  XOR2   g032(.A(new_n105_), .B(new_n80_), .Y(new_n106_));
  XOR2   g033(.A(new_n106_), .B(new_n102_), .Y(new_n107_));
  XOR2   g034(.A(new_n107_), .B(new_n101_), .Y(new_n108_));
  XOR2   g035(.A(G36gat), .B(G8gat), .Y(new_n109_));
  XOR2   g036(.A(G92gat), .B(G64gat), .Y(new_n110_));
  XOR2   g037(.A(new_n110_), .B(new_n109_), .Y(new_n111_));
  INV    g038(.A(new_n111_), .Y(new_n112_));
  NAND2  g039(.A(G233gat), .B(G226gat), .Y(new_n113_));
  XOR2   g040(.A(new_n105_), .B(new_n94_), .Y(new_n114_));
  XOR2   g041(.A(new_n114_), .B(new_n113_), .Y(new_n115_));
  XOR2   g042(.A(new_n115_), .B(new_n112_), .Y(new_n116_));
  NOR4   g043(.A(new_n116_), .B(new_n108_), .C(new_n97_), .D(new_n86_), .Y(new_n117_));
  INV    g044(.A(new_n76_), .Y(new_n118_));
  XOR2   g045(.A(new_n85_), .B(new_n118_), .Y(new_n119_));
  XOR2   g046(.A(new_n115_), .B(new_n111_), .Y(new_n120_));
  NOR4   g047(.A(new_n120_), .B(new_n108_), .C(new_n97_), .D(new_n119_), .Y(new_n121_));
  XOR2   g048(.A(new_n96_), .B(new_n89_), .Y(new_n122_));
  NOR4   g049(.A(new_n116_), .B(new_n108_), .C(new_n122_), .D(new_n119_), .Y(new_n123_));
  XOR2   g050(.A(new_n107_), .B(new_n100_), .Y(new_n124_));
  NOR4   g051(.A(new_n116_), .B(new_n124_), .C(new_n97_), .D(new_n119_), .Y(new_n125_));
  NOR4   g052(.A(new_n125_), .B(new_n123_), .C(new_n121_), .D(new_n117_), .Y(new_n126_));
  XOR2   g053(.A(G141gat), .B(G113gat), .Y(new_n127_));
  XOR2   g054(.A(G197gat), .B(G169gat), .Y(new_n128_));
  XOR2   g055(.A(new_n128_), .B(new_n127_), .Y(new_n129_));
  INV    g056(.A(new_n129_), .Y(new_n130_));
  NAND2  g057(.A(G233gat), .B(G229gat), .Y(new_n131_));
  XOR2   g058(.A(G8gat), .B(G1gat), .Y(new_n132_));
  XOR2   g059(.A(G22gat), .B(G15gat), .Y(new_n133_));
  XOR2   g060(.A(new_n133_), .B(new_n132_), .Y(new_n134_));
  XOR2   g061(.A(G36gat), .B(G29gat), .Y(new_n135_));
  XOR2   g062(.A(G50gat), .B(G43gat), .Y(new_n136_));
  XOR2   g063(.A(new_n136_), .B(new_n135_), .Y(new_n137_));
  XOR2   g064(.A(new_n137_), .B(new_n134_), .Y(new_n138_));
  XOR2   g065(.A(new_n138_), .B(new_n131_), .Y(new_n139_));
  XOR2   g066(.A(new_n139_), .B(new_n130_), .Y(new_n140_));
  XOR2   g067(.A(G148gat), .B(G120gat), .Y(new_n141_));
  XOR2   g068(.A(G204gat), .B(G176gat), .Y(new_n142_));
  XOR2   g069(.A(new_n142_), .B(new_n141_), .Y(new_n143_));
  NAND2  g070(.A(G233gat), .B(G230gat), .Y(new_n144_));
  XOR2   g071(.A(G64gat), .B(G57gat), .Y(new_n145_));
  XOR2   g072(.A(G78gat), .B(G71gat), .Y(new_n146_));
  XOR2   g073(.A(new_n146_), .B(new_n145_), .Y(new_n147_));
  XOR2   g074(.A(G92gat), .B(G85gat), .Y(new_n148_));
  XOR2   g075(.A(G106gat), .B(G99gat), .Y(new_n149_));
  XOR2   g076(.A(new_n149_), .B(new_n148_), .Y(new_n150_));
  XOR2   g077(.A(new_n150_), .B(new_n147_), .Y(new_n151_));
  XOR2   g078(.A(new_n151_), .B(new_n144_), .Y(new_n152_));
  XOR2   g079(.A(new_n152_), .B(new_n143_), .Y(new_n153_));
  NAND2  g080(.A(new_n153_), .B(new_n140_), .Y(new_n154_));
  XOR2   g081(.A(G162gat), .B(G134gat), .Y(new_n155_));
  XOR2   g082(.A(G218gat), .B(G190gat), .Y(new_n156_));
  XOR2   g083(.A(new_n156_), .B(new_n155_), .Y(new_n157_));
  NAND2  g084(.A(G233gat), .B(G232gat), .Y(new_n158_));
  XOR2   g085(.A(new_n150_), .B(new_n137_), .Y(new_n159_));
  XOR2   g086(.A(new_n159_), .B(new_n158_), .Y(new_n160_));
  XOR2   g087(.A(new_n160_), .B(new_n157_), .Y(new_n161_));
  XOR2   g088(.A(G155gat), .B(G127gat), .Y(new_n162_));
  XOR2   g089(.A(G211gat), .B(G183gat), .Y(new_n163_));
  XOR2   g090(.A(new_n163_), .B(new_n162_), .Y(new_n164_));
  INV    g091(.A(new_n164_), .Y(new_n165_));
  NAND2  g092(.A(G233gat), .B(G231gat), .Y(new_n166_));
  XOR2   g093(.A(new_n147_), .B(new_n134_), .Y(new_n167_));
  XOR2   g094(.A(new_n167_), .B(new_n166_), .Y(new_n168_));
  XOR2   g095(.A(new_n168_), .B(new_n165_), .Y(new_n169_));
  NAND2  g096(.A(new_n169_), .B(new_n161_), .Y(new_n170_));
  NOR4   g097(.A(new_n170_), .B(new_n154_), .C(new_n126_), .D(new_n86_), .Y(new_n171_));
  XOR2   g098(.A(new_n171_), .B(G1gat), .Y(G1324gat));
  NOR4   g099(.A(new_n170_), .B(new_n154_), .C(new_n126_), .D(new_n120_), .Y(new_n173_));
  XOR2   g100(.A(new_n173_), .B(G8gat), .Y(G1325gat));
  NOR4   g101(.A(new_n170_), .B(new_n154_), .C(new_n126_), .D(new_n124_), .Y(new_n175_));
  XOR2   g102(.A(new_n175_), .B(G15gat), .Y(G1326gat));
  NOR4   g103(.A(new_n170_), .B(new_n154_), .C(new_n126_), .D(new_n122_), .Y(new_n177_));
  XOR2   g104(.A(new_n177_), .B(G22gat), .Y(G1327gat));
  XOR2   g105(.A(new_n168_), .B(new_n164_), .Y(new_n179_));
  NAND3  g106(.A(new_n179_), .B(new_n153_), .C(new_n140_), .Y(new_n180_));
  NOR4   g107(.A(new_n180_), .B(new_n161_), .C(new_n126_), .D(new_n86_), .Y(new_n181_));
  XOR2   g108(.A(new_n181_), .B(G29gat), .Y(G1328gat));
  NOR4   g109(.A(new_n180_), .B(new_n161_), .C(new_n126_), .D(new_n120_), .Y(new_n183_));
  XOR2   g110(.A(new_n183_), .B(G36gat), .Y(G1329gat));
  NOR4   g111(.A(new_n180_), .B(new_n161_), .C(new_n126_), .D(new_n124_), .Y(new_n185_));
  XOR2   g112(.A(new_n185_), .B(G43gat), .Y(G1330gat));
  NOR4   g113(.A(new_n180_), .B(new_n161_), .C(new_n126_), .D(new_n122_), .Y(new_n187_));
  XOR2   g114(.A(new_n187_), .B(G50gat), .Y(G1331gat));
  XOR2   g115(.A(new_n139_), .B(new_n129_), .Y(new_n189_));
  INV    g116(.A(new_n143_), .Y(new_n190_));
  XOR2   g117(.A(new_n152_), .B(new_n190_), .Y(new_n191_));
  NAND2  g118(.A(new_n191_), .B(new_n189_), .Y(new_n192_));
  NOR4   g119(.A(new_n192_), .B(new_n170_), .C(new_n126_), .D(new_n86_), .Y(new_n193_));
  XOR2   g120(.A(new_n193_), .B(G57gat), .Y(G1332gat));
  NOR4   g121(.A(new_n192_), .B(new_n170_), .C(new_n126_), .D(new_n120_), .Y(new_n195_));
  XOR2   g122(.A(new_n195_), .B(G64gat), .Y(G1333gat));
  NOR4   g123(.A(new_n192_), .B(new_n170_), .C(new_n126_), .D(new_n124_), .Y(new_n197_));
  XOR2   g124(.A(new_n197_), .B(G71gat), .Y(G1334gat));
  NOR4   g125(.A(new_n192_), .B(new_n170_), .C(new_n126_), .D(new_n122_), .Y(new_n199_));
  XOR2   g126(.A(new_n199_), .B(G78gat), .Y(G1335gat));
  NAND3  g127(.A(new_n179_), .B(new_n191_), .C(new_n189_), .Y(new_n201_));
  NOR4   g128(.A(new_n201_), .B(new_n161_), .C(new_n126_), .D(new_n86_), .Y(new_n202_));
  XOR2   g129(.A(new_n202_), .B(G85gat), .Y(G1336gat));
  NOR4   g130(.A(new_n201_), .B(new_n161_), .C(new_n126_), .D(new_n120_), .Y(new_n204_));
  XOR2   g131(.A(new_n204_), .B(G92gat), .Y(G1337gat));
  NOR4   g132(.A(new_n201_), .B(new_n161_), .C(new_n126_), .D(new_n124_), .Y(new_n206_));
  XOR2   g133(.A(new_n206_), .B(G99gat), .Y(G1338gat));
  NOR4   g134(.A(new_n201_), .B(new_n161_), .C(new_n126_), .D(new_n122_), .Y(new_n208_));
  XOR2   g135(.A(new_n208_), .B(G106gat), .Y(G1339gat));
  NAND2  g136(.A(new_n120_), .B(new_n119_), .Y(new_n210_));
  NAND2  g137(.A(new_n108_), .B(new_n122_), .Y(new_n211_));
  INV    g138(.A(new_n157_), .Y(new_n212_));
  XOR2   g139(.A(new_n160_), .B(new_n212_), .Y(new_n213_));
  NOR4   g140(.A(new_n169_), .B(new_n213_), .C(new_n191_), .D(new_n189_), .Y(new_n214_));
  NOR4   g141(.A(new_n169_), .B(new_n213_), .C(new_n153_), .D(new_n140_), .Y(new_n215_));
  NOR4   g142(.A(new_n169_), .B(new_n161_), .C(new_n191_), .D(new_n140_), .Y(new_n216_));
  NOR4   g143(.A(new_n179_), .B(new_n213_), .C(new_n191_), .D(new_n140_), .Y(new_n217_));
  NOR4   g144(.A(new_n217_), .B(new_n216_), .C(new_n215_), .D(new_n214_), .Y(new_n218_));
  NOR4   g145(.A(new_n218_), .B(new_n189_), .C(new_n211_), .D(new_n210_), .Y(new_n219_));
  XOR2   g146(.A(new_n219_), .B(G113gat), .Y(G1340gat));
  NOR4   g147(.A(new_n218_), .B(new_n153_), .C(new_n211_), .D(new_n210_), .Y(new_n221_));
  XOR2   g148(.A(new_n221_), .B(G120gat), .Y(G1341gat));
  NOR4   g149(.A(new_n218_), .B(new_n179_), .C(new_n211_), .D(new_n210_), .Y(new_n223_));
  XOR2   g150(.A(new_n223_), .B(G127gat), .Y(G1342gat));
  NOR4   g151(.A(new_n218_), .B(new_n161_), .C(new_n211_), .D(new_n210_), .Y(new_n225_));
  XOR2   g152(.A(new_n225_), .B(G134gat), .Y(G1343gat));
  NAND3  g153(.A(new_n120_), .B(new_n124_), .C(new_n119_), .Y(new_n227_));
  NOR4   g154(.A(new_n218_), .B(new_n189_), .C(new_n227_), .D(new_n122_), .Y(new_n228_));
  XOR2   g155(.A(new_n228_), .B(G141gat), .Y(G1344gat));
  NOR4   g156(.A(new_n218_), .B(new_n153_), .C(new_n227_), .D(new_n122_), .Y(new_n230_));
  XOR2   g157(.A(new_n230_), .B(G148gat), .Y(G1345gat));
  NOR4   g158(.A(new_n218_), .B(new_n179_), .C(new_n227_), .D(new_n122_), .Y(new_n232_));
  XOR2   g159(.A(new_n232_), .B(G155gat), .Y(G1346gat));
  NOR4   g160(.A(new_n218_), .B(new_n161_), .C(new_n227_), .D(new_n122_), .Y(new_n234_));
  XOR2   g161(.A(new_n234_), .B(G162gat), .Y(G1347gat));
  NAND2  g162(.A(new_n116_), .B(new_n86_), .Y(new_n236_));
  NOR4   g163(.A(new_n218_), .B(new_n189_), .C(new_n211_), .D(new_n236_), .Y(new_n237_));
  XOR2   g164(.A(new_n237_), .B(G169gat), .Y(G1348gat));
  NOR4   g165(.A(new_n218_), .B(new_n153_), .C(new_n211_), .D(new_n236_), .Y(new_n239_));
  XOR2   g166(.A(new_n239_), .B(G176gat), .Y(G1349gat));
  NOR4   g167(.A(new_n218_), .B(new_n179_), .C(new_n211_), .D(new_n236_), .Y(new_n241_));
  XOR2   g168(.A(new_n241_), .B(G183gat), .Y(G1350gat));
  NOR4   g169(.A(new_n218_), .B(new_n161_), .C(new_n211_), .D(new_n236_), .Y(new_n243_));
  XOR2   g170(.A(new_n243_), .B(G190gat), .Y(G1351gat));
  NAND3  g171(.A(new_n116_), .B(new_n124_), .C(new_n86_), .Y(new_n245_));
  NOR4   g172(.A(new_n218_), .B(new_n189_), .C(new_n245_), .D(new_n122_), .Y(new_n246_));
  XOR2   g173(.A(new_n246_), .B(G197gat), .Y(G1352gat));
  NOR4   g174(.A(new_n218_), .B(new_n153_), .C(new_n245_), .D(new_n122_), .Y(new_n248_));
  XOR2   g175(.A(new_n248_), .B(G204gat), .Y(G1353gat));
  NOR4   g176(.A(new_n218_), .B(new_n179_), .C(new_n245_), .D(new_n122_), .Y(new_n250_));
  XOR2   g177(.A(new_n250_), .B(G211gat), .Y(G1354gat));
  NOR4   g178(.A(new_n218_), .B(new_n161_), .C(new_n245_), .D(new_n122_), .Y(new_n252_));
  XOR2   g179(.A(new_n252_), .B(G218gat), .Y(G1355gat));
endmodule


