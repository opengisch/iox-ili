package ch.interlis.iox_j.validator;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import ch.ehi.basics.settings.Settings;
import ch.interlis.ili2c.config.Configuration;
import ch.interlis.ili2c.config.FileEntry;
import ch.interlis.ili2c.config.FileEntryKind;
import ch.interlis.ili2c.metamodel.TransferDescription;
import ch.interlis.iom_j.Iom_jObject;
import ch.interlis.iox_j.EndBasketEvent;
import ch.interlis.iox_j.EndTransferEvent;
import ch.interlis.iox_j.ObjectEvent;
import ch.interlis.iox_j.StartBasketEvent;
import ch.interlis.iox_j.StartTransferEvent;
import ch.interlis.iox_j.logging.LogEventFactory;

public class Association23Test {

	private TransferDescription td=null;
	// OID
	private final static String OID1 ="o1";
	private final static String OID2 ="o2";
	private final static String OID3 ="o3";
	private final static String OBJ_OID4 ="o4";
	private final static String OBJ_OID5 ="o5";
	private final static String OBJ_OID6 ="o6";
	private final static String OBJ_OID7 ="o7";
	// MODEL.TOPIC
	private final static String ILI_TOPIC="Association23.Topic";
	private final static String ILI_TOPICB="Association23.TopicB";
	// CLASS
	private final static String ILI_CLASSA=ILI_TOPIC+".ClassA";
	private final static String ILI_CLASSB=ILI_TOPIC+".ClassB";
	private final static String ILI_CLASSD=ILI_TOPIC+".ClassD";
	private final static String ILI_CLASSE=ILI_TOPIC+".ClassE";
	private final static String ILI_CLASSF=ILI_TOPIC+".ClassF";
	private final static String ILI_CLASSG=ILI_TOPIC+".ClassG";
	private final static String ILI_CLASSH=ILI_TOPIC+".ClassH";
	private final static String ILI_CLASSI=ILI_TOPIC+".ClassI";
	private final static String ILI_CLASSJ=ILI_TOPIC+".ClassJ";
	private final static String ILI_TOPICB_CLASSE=ILI_TOPICB+".ClassE";
	private final static String ILI_TOPICB_CLASSF=ILI_TOPICB+".ClassF";
	private final static String ILI_TOPICB_CLASSG=ILI_TOPICB+".ClassG";
	private final static String ILI_TOPICB_CLASSH=ILI_TOPICB+".ClassH";
	// CLASS EXTEND
	private final static String ILI_CLASSAP=ILI_TOPIC+".ClassAp";
	private final static String ILI_CLASSBP=ILI_TOPIC+".ClassBp";
	private final static String ILI_CLASSCP=ILI_TOPIC+".ClassCp";
	// ASSOCIATION
	private final static String ILI_ASSOC_AB1_A1="a1";
	private final static String ILI_ASSOC_AB1_B1="b1";
	
	private final static String ILI_ASSOC_AB2_A2="a2";
	private final static String ILI_ASSOC_AB2_B2="b2";
	
	private final static String ILI_ASSOC_AB3_A3="a3";
	private final static String ILI_ASSOC_AB3_B3="b3";
	
	private final static String ILI_ASSOC_AB4_A4="a4";
	private final static String ILI_ASSOC_AB4_B4="b4";
	
	private final static String ILI_ASSOC_ABP1_AP1="ap1";
	private final static String ILI_ASSOC_ABP1_BP1="bp1";
	
	private final static String ILI_ASSOC_ABP2_AP2="ap2";
	private final static String ILI_ASSOC_ABP2_BP2="bp2";
	
	private final static String ILI_ASSOC_ABP3_BP3="bp3";
	private final static String ILI_ASSOC_ABP3_AP3="ap3";
	
	private final static String ILI_ASSOC_ABD1_AD1="ad1";
	private final static String ILI_ASSOC_ABD1_BD1="bd1";
	
	private final static String ILI_ASSOC_ABD2_AD2="ad2";
	private final static String ILI_ASSOC_ABD2_BD2="bd2";
	
	private final static String ILI_ASSOC_ABD3_AD3="ad3";
	private final static String ILI_ASSOC_ABD3_BD3="bd3";
	
	private final static String ILI_ASSOC_EF1_E1="e1";
	private final static String ILI_ASSOC_EF1_F1="f1";
	
	private final static String ILI_ASSOC_GH1_G1="g1";
	private final static String ILI_ASSOC_GH1_H1="h1";
	
	// ASSOCIATION CLASS
	private final static String ILI_ASSOC_AB2=ILI_TOPIC+".ab2";
	private final static String ILI_ASSOC_ABP2=ILI_TOPIC+".abp2";
	private final static String ILI_ASSOC_ABD2=ILI_TOPIC+".abd2";
	private final static String ILI_ASSOC_EF1=ILI_TOPIC+".ef1";
	private final static String ILI_ASSOC_GH1=ILI_TOPICB+".gh1";
	private final static String ILI_TOPICB_ASSOC_EF1=ILI_TOPICB+".ef1";
	private final static String ILI_TOPICB_ASSOC_EF1_E1="e1";
	private final static String ILI_TOPICB_ASSOC_EF1_F1="f1";
	
	// START BASKET EVENT
	private final static String BASKET_ID1="b1";
	private final static String BASKET_ID2="b2";
	
	@Before
	public void setUp() throws Exception {
		// ili-datei lesen
		Configuration ili2cConfig=new Configuration();
		FileEntry fileEntry=new FileEntry("src/test/data/validator/Association23.ili", FileEntryKind.ILIMODELFILE);
		ili2cConfig.addFileEntry(fileEntry);
		td=ch.interlis.ili2c.Ili2c.runCompiler(ili2cConfig);
		assertNotNull(td);
	}

	// Wenn von der KlasseB eine Beziehung zur KlasseA �ber den Rollennamen: a1, 1 Mal besteht soll keine Fehlermeldung ausgegeben werden. 
	@Test
	public void embeddedAsso_Ok(){
		Iom_jObject iomObjA=new Iom_jObject(ILI_CLASSA, OID1);
		Iom_jObject iomObjB=new Iom_jObject(ILI_CLASSB, OID2);
		iomObjB.addattrobj(ILI_ASSOC_AB1_A1, "REF").setobjectrefoid(OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjA));
		validator.validate(new ObjectEvent(iomObjB));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	// Wenn von der KlasseB, welche �ber KlasseBP eine Beziehung zur KlasseA �ber die KlasseAP �ber den Rollennamen: a1,
	// 1 Mal besteht soll keine Fehlermeldung ausgegeben werden.
	@Test
	public void embeddedAsso_RestrictionTargetClassFound_Ok(){
		Iom_jObject iomObjA=new Iom_jObject(ILI_CLASSAP, OID1);
		Iom_jObject iomObjB=new Iom_jObject(ILI_CLASSBP, OID2);
		iomObjB.addattrobj(ILI_ASSOC_ABP1_AP1, "REF").setobjectrefoid(OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjA));
		validator.validate(new ObjectEvent(iomObjB));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}	
	
	// Wenn von der KlasseB eine Beziehung zur KlasseA �ber den Rollennamen: ad1,
	// Von der KlasseB eine Beziehung zur KlasseD �ber den Rollennamen: ad1,
	// je, 0-1 Mal besteht und eine davon richtig ist, soll keine Fehlermeldung ausgegeben werden.
	@Test
	public void embeddedAsso_BooleanOperatorOR_TargetClassFound_Ok(){
		Iom_jObject iomObjA=new Iom_jObject(ILI_CLASSA, OID1);
		Iom_jObject iomObjB=new Iom_jObject(ILI_CLASSB, OID2);
		Iom_jObject iomObjD=new Iom_jObject(ILI_CLASSD, OID3);
		iomObjB.addattrobj(ILI_ASSOC_ABD1_AD1, "REF").setobjectrefoid(OID1);
		Iom_jObject iomObjB2=new Iom_jObject(ILI_CLASSB, OBJ_OID4);
		iomObjB2.addattrobj(ILI_ASSOC_ABD1_AD1, "REF").setobjectrefoid(OID3);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjA));
		validator.validate(new ObjectEvent(iomObjB));
		validator.validate(new ObjectEvent(iomObjB2));
		validator.validate(new ObjectEvent(iomObjD));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	// Wenn von der KlasseH eine Beziehung zur KlasseG �ber den Rollennamen: g1 (Welcher EXTERNAL true ist),
	// 1 Mal besteht soll. Die Klasse jedoch in unterschiedlichen Baskets sich befinden.
	// keine Fehlermeldung ausgegeben werden.
	@Test
	public void external_RoleEmbeddedAsso_DifferentBaskets_TargetObjectFound_Ok(){
		Iom_jObject iomObjG=new Iom_jObject(ILI_TOPICB_CLASSG, OID1);
		Iom_jObject iomObjH1=new Iom_jObject(ILI_TOPICB_CLASSH, OID2);
		iomObjH1.addattrobj("g1", "REF").setobjectrefoid(OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjG));
		validator.validate(new EndBasketEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID2));
		validator.validate(new ObjectEvent(iomObjH1));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(0,logger.getErrs().size());
	}
	
	// Wenn in einer Stand Alone Association von der KlasseB eine Beziehung zur KlasseA �ber den Rollennamen: a1,
	// Von der Klasse A eine Beziehung zur KlasseB �ber den Rollennamen: b1,
	// je, 1 Mal besteht soll keine Fehlermeldung ausgegeben werden.
	@Test
	public void standAloneAsso_0toN_Ok(){
		Iom_jObject iomObjA=new Iom_jObject(ILI_CLASSA, OID1);
		Iom_jObject iomObjB=new Iom_jObject(ILI_CLASSB, OID2);
		Iom_jObject iomObjAB=new Iom_jObject(ILI_ASSOC_AB2, null);
		iomObjAB.addattrobj(ILI_ASSOC_AB2_A2, "REF").setobjectrefoid(OID1);
		iomObjAB.addattrobj(ILI_ASSOC_AB2_B2, "REF").setobjectrefoid(OID2);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjA));
		validator.validate(new ObjectEvent(iomObjB));
		validator.validate(new ObjectEvent(iomObjAB));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	// Wenn in einer Stand Alone Association von der KlasseF eine Beziehung zur KlasseE �ber den Rollennamen: e1,
	// Von der KlasseE eine Beziehung zur KlasseF �ber den Rollennamen: f1,
	// je, 1 Mal besteht soll keine Fehlermeldung ausgegeben werden.
	@Test
	public void standAloneAsso_1toN_Ok(){
		Iom_jObject iomObjE=new Iom_jObject(ILI_CLASSE, OID1);
		Iom_jObject iomObjF=new Iom_jObject(ILI_CLASSF, OID2);
		Iom_jObject iomLinkEF=new Iom_jObject(ILI_ASSOC_EF1, null);
		iomLinkEF.addattrobj(ILI_ASSOC_EF1_E1, "REF").setobjectrefoid(OID1);
		iomLinkEF.addattrobj(ILI_ASSOC_EF1_F1, "REF").setobjectrefoid(OID2);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjE));
		validator.validate(new ObjectEvent(iomObjF));
		validator.validate(new ObjectEvent(iomLinkEF));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(0,logger.getErrs().size());
	}
	
	// Wenn in einer Stand Alone Association von der KlasseF eine Beziehung zur KlasseE �ber den Rollennamen: e1,
	// Von der KlasseE eine Beziehung zur KlasseF �ber den Rollennamen: f1,
	// je, 1 Mal besteht soll keine Fehlermeldung ausgegeben werden,
	// wenn die Objekte sich in der gleichen Basket befinden und External true ist.	
	@Test
	public void external_StandAloneAsso_SameBasket_Ok(){
		Iom_jObject iomObjE=new Iom_jObject(ILI_TOPICB_CLASSE, OID1);
		Iom_jObject iomObjF=new Iom_jObject(ILI_TOPICB_CLASSF, OID2);
		Iom_jObject iomLinkEF=new Iom_jObject(ILI_TOPICB_ASSOC_EF1, null);
		iomLinkEF.addattrobj(ILI_TOPICB_ASSOC_EF1_E1, "REF").setobjectrefoid(OID1);
		iomLinkEF.addattrobj(ILI_TOPICB_ASSOC_EF1_F1, "REF").setobjectrefoid(OID2);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjE));
		validator.validate(new ObjectEvent(iomObjF));
		validator.validate(new ObjectEvent(iomLinkEF));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(0,logger.getErrs().size());
	}
	
	// Wenn in einer Embedded Association von der KlasseH eine Beziehung zur KlasseG �ber den Rollennamen: g1,
	// die Klasse nicht gefunden werden kann, die Rolle: External=True ist, soll keine Fehlermeldung ausgegeben werden.
	@Test
	public void external_EmbeddedAsso_Ok(){
		Iom_jObject iomObjH1=new Iom_jObject("Association23.TopicB.ClassH", OID2);
		iomObjH1.addattrobj("g1", "REF").setobjectrefoid(OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjH1));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(0,logger.getErrs().size());
	}
	
	// External=true, Objects in different Baskets.
	// Ergibt einen Fehler! Da jedoch External=true ist, wird dieser nicht ausgegeben.
	@Test
	public void external_EmbeddedAsso_DiffBasket_Ok(){
		Iom_jObject iomObjG1=new Iom_jObject("Association23.TopicB.ClassG", OID1);
		Iom_jObject iomObjH1=new Iom_jObject("Association23.TopicB.ClassH", OID2);
		iomObjH1.addattrobj("g1", "REF").setobjectrefoid(OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjH1));
		validator.validate(new EndBasketEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID2));
		validator.validate(new ObjectEvent(iomObjG1));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(0,logger.getErrs().size());
	}
	
	// Wenn in einer Stand Alone Association von der KlasseF eine Beziehung zur KlasseE �ber den Rollennamen: e1,
	// Von der KlasseE eine Beziehung zur KlasseF �ber den Rollennamen: f1,
	// je, 1 Mal besteht soll keine Fehlermeldung ausgegeben werden,
	// wenn die Objekte sich in unterschiedlichen Baskets befinden und External true ist.
	@Test
	public void external_StandAloneAsso_DiffBasket_Ok(){
		Iom_jObject iomObjE=new Iom_jObject(ILI_TOPICB_CLASSE, OID1);
		Iom_jObject iomObjF=new Iom_jObject(ILI_TOPICB_CLASSF, OID2);
		Iom_jObject iomLinkEF=new Iom_jObject(ILI_TOPICB_ASSOC_EF1, null);
		iomLinkEF.addattrobj(ILI_TOPICB_ASSOC_EF1_E1, "REF").setobjectrefoid(OID1);
		iomLinkEF.addattrobj(ILI_TOPICB_ASSOC_EF1_F1, "REF").setobjectrefoid(OID2);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjE));
		validator.validate(new EndBasketEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID2));
		validator.validate(new ObjectEvent(iomObjF));
		validator.validate(new ObjectEvent(iomLinkEF));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(0,logger.getErrs().size());
	}
	
	// Wenn in einer Stand Alone Association von der KlasseF eine Beziehung zur KlasseE �ber den Rollennamen: e1,
	// Von der KlasseE eine Beziehung zur KlasseF �ber den Rollennamen: f1,
	// je, 1 Mal besteht soll keine Fehlermeldung ausgegeben werden,
	// wenn die Objekte sich im gleichen Basket befindet und External true ist.
	@Test
	public void external_StandAloneAsso_SameBasketNtoN_Ok(){
		Iom_jObject iomObjF=new Iom_jObject(ILI_TOPICB_CLASSF, OID2);
		Iom_jObject iomLinkEF=new Iom_jObject(ILI_TOPICB_ASSOC_EF1, null);
		iomLinkEF.addattrobj(ILI_TOPICB_ASSOC_EF1_E1, "REF").setobjectrefoid(OID1);
		iomLinkEF.addattrobj(ILI_TOPICB_ASSOC_EF1_F1, "REF").setobjectrefoid(OID2);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID2));
		validator.validate(new ObjectEvent(iomObjF));
		validator.validate(new ObjectEvent(iomLinkEF));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(0,logger.getErrs().size());
	}
	
	// Wenn von einer Stand Alone Association der KlasseB, welche �ber KlasseBP eine Beziehung zur KlasseA �ber die KlasseAP �ber den Rollennamen: a1,
	// von der KlasseA, welche �ber KlasseAP eine Beziehung zur KlasseB �ber die KlasseBP �ber den Rollennamen: b1,
	// je, 1 Mal besteht soll keine Fehlermeldung ausgegeben werden. Gleicher Basket, External false.
	@Test
	public void standAloneAsso_RestrictionTargetClassFound_Ok(){	
		Iom_jObject iomObjAp=new Iom_jObject(ILI_CLASSAP, OID1);
		Iom_jObject iomObjBp=new Iom_jObject(ILI_CLASSBP, OID2);
		Iom_jObject iomObjABP=new Iom_jObject(ILI_ASSOC_ABP2, null);
		iomObjABP.addattrobj(ILI_ASSOC_ABP2_AP2, "REF").setobjectrefoid(OID1);
		iomObjABP.addattrobj(ILI_ASSOC_ABP2_BP2, "REF").setobjectrefoid(OID2);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjAp));
		validator.validate(new ObjectEvent(iomObjBp));
		validator.validate(new ObjectEvent(iomObjABP));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	// Wenn von einer Stand Alone Association von der KlasseB eine Beziehung zur KlasseA �ber den Rollennamen: ad2,
	// Von der KlasseB eine Beziehung zur KlasseD �ber den Rollennamen: ad2,
	// je, 1 Mal besteht und eine davon richtig ist, soll keine Fehlermeldung ausgegeben werden.
	@Test
	public void standAloneAsso_BooleanOperatorOR_Ok(){
		Iom_jObject iomObjA=new Iom_jObject(ILI_CLASSA, OID1);
		Iom_jObject iomObjB=new Iom_jObject(ILI_CLASSB, OID2);
		Iom_jObject iomObjAB=new Iom_jObject(ILI_ASSOC_ABD2, null);
		iomObjAB.addattrobj(ILI_ASSOC_ABD2_AD2, "REF").setobjectrefoid(OID1);
		iomObjAB.addattrobj(ILI_ASSOC_ABD2_BD2, "REF").setobjectrefoid(OID2);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjA));
		validator.validate(new ObjectEvent(iomObjB));
		validator.validate(new ObjectEvent(iomObjAB));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	// Es wird getestet ob eine Fehlermeldung ausgegeben wird, wenn:
	// - Die role e1 (EXTERNAL true) und f1 (EXTERNAL true) der Association EF1 aufgerufen wird.
	// - Die target class E, innerhalb der basket nicht gefunden werden kann.
	// - Die target class in der Basket b2 existiert.
	@Test
	public void resolver_StandAloneAsso_ObjectFound_Ok(){
		Iom_jObject iomObjE=new Iom_jObject(ILI_TOPICB_CLASSE, OID1);
		Iom_jObject iomObjF=new Iom_jObject(ILI_TOPICB_CLASSF, OID2);
		Iom_jObject iomLinkEF=new Iom_jObject(ILI_TOPICB_ASSOC_EF1, null);
		iomLinkEF.addattrobj(ILI_TOPICB_ASSOC_EF1_E1, "REF").setobjectrefoid(ExternalObjResolverMock.OID1);
		iomLinkEF.addattrobj(ILI_TOPICB_ASSOC_EF1_F1, "REF").setobjectrefoid(OID2);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		List<Class> resolverClasses=new ArrayList<Class>();
		resolverClasses.add(ExternalObjResolverMock.class);
		settings.setTransientObject(Validator.CONFIG_OBJECT_RESOLVERS, resolverClasses);
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjF));
		validator.validate(new ObjectEvent(iomLinkEF));
		validator.validate(new EndBasketEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID2));
		validator.validate(new ObjectEvent(iomObjE));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(1,logger.getErrs().size());
	}
	
	// Es wird getestet ob eine Fehlermeldung ausgegeben wird, wenn:
	// - Die role g1 (EXTERNAL true) der Association GH1 aufgerufen wird.
	// - Die target class G, innerhalb der basket nicht gefunden werden kann.
	// - Die target class in der Basket b2 existiert.
	@Test
	public void resolver_EmbeddedAsso_ObjectFound_Ok(){
		Iom_jObject iomObjG=new Iom_jObject(ILI_TOPICB_CLASSG, OID1);
		Iom_jObject iomObjH1=new Iom_jObject(ILI_TOPICB_CLASSH, OID2);
		iomObjH1.addattrobj("g1", "REF").setobjectrefoid(ExternalObjResolverMock.OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		List<Class> resolverClasses=new ArrayList<Class>();
		resolverClasses.add(ExternalObjResolverMock.class);
		settings.setTransientObject(Validator.CONFIG_OBJECT_RESOLVERS, resolverClasses);
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjH1));
		validator.validate(new EndBasketEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID2));
		validator.validate(new ObjectEvent(iomObjG));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(1,logger.getErrs().size());
	}
	
	
	// External=false, Objects in different Baskets.
	// Ergibt einen Fehler!
	@Test
	public void embeddedAsso_DifferentBaskets_False(){
		Iom_jObject iomObjG1=new Iom_jObject("Association23.Topic.ClassG", OID1);
		Iom_jObject iomObjH1=new Iom_jObject("Association23.Topic.ClassH", OID2);
		iomObjH1.addattrobj("g1", "REF").setobjectrefoid(OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjH1));
		validator.validate(new EndBasketEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID2));
		validator.validate(new ObjectEvent(iomObjG1));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("No object found with OID o1 in basket b1.", logger.getErrs().get(0).getEventMsg());
	}
	
	// Wenn in einer Stand Alone Association von der KlasseF eine Beziehung zur KlasseE �ber den Rollennamen: e1,
	// Von der KlasseE eine Beziehung zur KlasseF �ber den Rollennamen: f1,
	// je, 1 Mal besteht soll eine Fehlermeldung ausgegeben werden,
	// wenn die Objekte sich in unterschiedlichen Baskets befinden und External false ist.
	@Test
	public void standAloneAsso_DifferentBaskets_False(){
		Iom_jObject iomObjE=new Iom_jObject(ILI_CLASSE, OID1);
		Iom_jObject iomObjF=new Iom_jObject(ILI_CLASSF, OID2);
		Iom_jObject iomLinkEF=new Iom_jObject(ILI_ASSOC_EF1, null);
		iomLinkEF.addattrobj(ILI_ASSOC_EF1_E1, "REF").setobjectrefoid(OID1);
		iomLinkEF.addattrobj(ILI_ASSOC_EF1_F1, "REF").setobjectrefoid(OID2);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjE));
		validator.validate(new EndBasketEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID2));
		validator.validate(new ObjectEvent(iomObjF));
		validator.validate(new ObjectEvent(iomLinkEF));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(1,logger.getErrs().size());
		assertEquals("No object found with OID o1 in basket b2.", logger.getErrs().get(0).getEventMsg());
	}	
	
	// Wenn von der KlasseB eine Beziehung zur KlasseA �ber den Rollennamen: a1, 0 bis 1 Mal besteht soll keine Fehlermeldung ausgegeben werden. 
	@Test
	public void embeddedAsso_WrongCardinality_Fail(){
		Iom_jObject iomObjA=new Iom_jObject(ILI_CLASSA, OID1);
		Iom_jObject iomObjB1=new Iom_jObject(ILI_CLASSB, OID2);
		iomObjB1.addattrobj(ILI_ASSOC_AB1_A1, "REF").setobjectrefoid(OID1);
		Iom_jObject iomObjB2=new Iom_jObject(ILI_CLASSB, OID3);
		iomObjB2.addattrobj(ILI_ASSOC_AB1_A1, "REF").setobjectrefoid(OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjA));
		validator.validate(new ObjectEvent(iomObjB1));
		validator.validate(new ObjectEvent(iomObjB2));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("b1 should associate 0 to 1 target objects (instead of 2)", logger.getErrs().get(0).getEventMsg());
	}
	
	// Wenn von der KlasseB, welche �ber KlasseBP eine Beziehung zur KlasseA �ber die KlasseAP �ber den Rollennamen: a1,
	// 1 Mal besteht soll keine Fehlermeldung ausgegeben werden.
	@Test
	public void embeddedAsso_CardinalityExtendedClass_Fail(){
		Iom_jObject iomObjA=new Iom_jObject(ILI_CLASSAP, OID1);
		Iom_jObject iomObjB1=new Iom_jObject(ILI_CLASSBP, OID2);
		iomObjB1.addattrobj(ILI_ASSOC_ABP1_AP1, "REF").setobjectrefoid(OID1);
		Iom_jObject iomObjB2=new Iom_jObject(ILI_CLASSBP, OID3);
		iomObjB2.addattrobj(ILI_ASSOC_ABP1_AP1, "REF").setobjectrefoid(OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjA));
		validator.validate(new ObjectEvent(iomObjB1));
		validator.validate(new ObjectEvent(iomObjB2));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("bp1 should associate 0 to 1 target objects (instead of 2)", logger.getErrs().get(0).getEventMsg());
	}
	
	// Wenn von der KlasseB eine Beziehung zur KlasseA �ber den Rollennamen: ad1,
	// Von der KlasseB eine Beziehung zur KlasseD �ber den Rollennamen: ad1,
	// je, 0-1 Mal besteht und eine davon richtig ist, soll keine Fehlermeldung ausgegeben werden.
	@Test
	public void embeddedAsso_BooleanOperatorOR_Fail(){
		Iom_jObject iomObjA=new Iom_jObject(ILI_CLASSA, OID1);
		Iom_jObject iomObjD=new Iom_jObject(ILI_CLASSD, OBJ_OID4);
		Iom_jObject iomObjB2=new Iom_jObject(ILI_CLASSB, OID2);
		iomObjB2.addattrobj(ILI_ASSOC_ABD1_AD1, "REF").setobjectrefoid(OID1);
		iomObjB2.addattrobj(ILI_ASSOC_ABD1_AD1, "REF").setobjectrefoid(OBJ_OID4);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjA));
		validator.validate(new ObjectEvent(iomObjD));
		validator.validate(new ObjectEvent(iomObjB2));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("ad1 should associate 0 to 1 target objects (instead of 2)", logger.getErrs().get(0).getEventMsg());
	}
		
	// Wenn von der KlasseB eine Beziehung zur KlasseA �ber den Rollennamen: a1, 0 bis 1 Mal besteht soll keine Fehlermeldung ausgegeben werden. 
	@Test
	public void embeddedAsso_TargetClassWrong_Fail(){
		Iom_jObject iomObjD=new Iom_jObject(ILI_CLASSD, OID1);
		Iom_jObject iomObjB=new Iom_jObject(ILI_CLASSB, OID2);
		iomObjB.addattrobj(ILI_ASSOC_AB1_A1, "REF").setobjectrefoid(OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjD));
		validator.validate(new ObjectEvent(iomObjB));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("wrong class Association23.Topic.ClassD of target object o1 for role Association23.Topic.ab1.a1.", logger.getErrs().get(0).getEventMsg());
	}
	
	// Es wird eine Fehlermeldung ausgegeben wenn in einer StandAlone Association eine falsche Kardinalit�t von e1 erstellt wurde.
	@Test
	public void standAloneAsso_CardinalityWrong_Fail(){
		Iom_jObject iomObjF=new Iom_jObject(ILI_CLASSF, OID2);
		Iom_jObject iomObjAp=new Iom_jObject(ILI_ASSOC_EF1, null);
		iomObjAp.addattrobj(ILI_ASSOC_EF1_F1, "REF").setobjectrefoid(OID2);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjF));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("e1 should associate 1 to * target objects (instead of 0)", logger.getErrs().get(0).getEventMsg());
	}
	
	// Die OID a1 der Klasse A, welche von der Klasse B mit der Rolle b1 Verbunden wird, existiert nicht.
	@Test
	public void embeddedAsso_OIDNotFound_Fail(){
		Iom_jObject iomObjA=new Iom_jObject(ILI_CLASSA, OID1);
		Iom_jObject iomObjB=new Iom_jObject(ILI_CLASSB, OID2);
		iomObjB.addattrobj(ILI_ASSOC_AB3_A3, "REF").setobjectrefoid(OID3);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjA));
		validator.validate(new ObjectEvent(iomObjB));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("No object found with OID o3 in basket b1.", logger.getErrs().get(0).getEventMsg());
	}
	
	// Die KlasseA mit der OID a1, verbindet �ber die Klasse: classBp mit der OID b1.
	// Es soll eine Fehlermeldung ausgegeben werden, wenn diese Klasse nicht existiert.
	@Test
	public void embeddedAsso_WrongTargetClass_Fail(){
		Iom_jObject iomObjC=new Iom_jObject(ILI_CLASSCP, OID1);
		Iom_jObject iomObjB=new Iom_jObject(ILI_CLASSBP, OID2);
		iomObjB.addattrobj(ILI_ASSOC_ABP1_AP1, "REF").setobjectrefoid(OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjC));
		validator.validate(new ObjectEvent(iomObjB));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("wrong class Association23.Topic.ClassCp of target object o1 for role Association23.Topic.abp1.ap1.", logger.getErrs().get(0).getEventMsg());
	}
	
	// Es wird getestet ob eine Fehlermeldung ausgegeben wird, wenn die Klasse der referenzierten oid: oid3, der Association abp3, nicht exisitiert.
	@Test
	public void embeddedAsso_TargetExtendedOIDNotFound_Fail(){
		Iom_jObject iomObjA=new Iom_jObject(ILI_CLASSAP, OID1);
		Iom_jObject iomObjB=new Iom_jObject(ILI_CLASSBP, OID2);
		iomObjB.addattrobj(ILI_ASSOC_ABP3_AP3, "REF").setobjectrefoid(OID3);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjA));
		validator.validate(new ObjectEvent(iomObjB));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("No object found with OID o3 in basket b1.", logger.getErrs().get(0).getEventMsg());
	}
	
	// Die Klasse der von der Association referenzierten oid2, existiert nicht.
	@Test
	public void standAloneAsso_TargetExtendedOIDNotFound_Fail(){
		Iom_jObject iomObjA=new Iom_jObject(ILI_CLASSAP, OID1);
		Iom_jObject iomObjB=new Iom_jObject(ILI_CLASSBP, OID2);
		Iom_jObject iomObjAp=new Iom_jObject(ILI_ASSOC_ABP2, null);
		iomObjAp.addattrobj(ILI_ASSOC_ABP2_AP2, "REF").setobjectrefoid(OID1);
		iomObjAp.addattrobj(ILI_ASSOC_ABP2_BP2, "REF").setobjectrefoid(OID3);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjA));
		validator.validate(new ObjectEvent(iomObjB));
		validator.validate(new ObjectEvent(iomObjAp));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("No object found with OID o3 in basket b1.", logger.getErrs().get(0).getEventMsg());
	}
	
	// Es soll getestet werden, ob eine Fehlermeldung ausgegeben wird, wenn die Multiplizit�t der Rolle Maximal 1 sein darf, jedoch 5 Objekte erstellt wurden.
	@Test
	public void embeddedAsso_ObjectRangeOfMultiplicityExceeded_Fail(){
		Iom_jObject iomObjG=new Iom_jObject(ILI_CLASSG, OID1);
		Iom_jObject iomObjH1=new Iom_jObject(ILI_CLASSH, OID2);
		iomObjH1.addattrobj("g1", "REF").setobjectrefoid(OID1);
		Iom_jObject iomObjH2=new Iom_jObject(ILI_CLASSH, OID3);
		iomObjH2.addattrobj("g1", "REF").setobjectrefoid(OID1);
		Iom_jObject iomObjH3=new Iom_jObject(ILI_CLASSH, OBJ_OID4);
		iomObjH3.addattrobj("g1", "REF").setobjectrefoid(OID1);
		Iom_jObject iomObjH4=new Iom_jObject(ILI_CLASSH, OBJ_OID5);
		iomObjH4.addattrobj("g1", "REF").setobjectrefoid(OID1);
		Iom_jObject iomObjH5=new Iom_jObject(ILI_CLASSH, OBJ_OID6);
		iomObjH5.addattrobj("g1", "REF").setobjectrefoid(OID1);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPIC,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjG));
		validator.validate(new ObjectEvent(iomObjH1));
		validator.validate(new ObjectEvent(iomObjH2));
		validator.validate(new ObjectEvent(iomObjH3));
		validator.validate(new ObjectEvent(iomObjH4));
		validator.validate(new ObjectEvent(iomObjH5));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("h1 should associate 1 to 1 target objects (instead of 5)", logger.getErrs().get(0).getEventMsg());
	}
	
	// Es wird getestet ob eine Fehlermeldung ausgegeben wird, wenn:
	// - Die role e1 (EXTERNAL true) und f1 (EXTERNAL true) der Association EF1 aufgerufen wird.
	// - Die target class E, innerhalb der basket nicht gefunden werden kann.
	// - Die target class E in keiner anderen basket existiert.
	@Test
	public void resolver_StandAloneAsso_TargetOIDNotFound_False(){
		Iom_jObject iomObjE=new Iom_jObject(ILI_TOPICB_CLASSE, OID1);
		Iom_jObject iomObjF=new Iom_jObject(ILI_TOPICB_CLASSF, OID2);
		Iom_jObject iomLinkEF=new Iom_jObject(ILI_TOPICB_ASSOC_EF1, null);
		iomLinkEF.addattrobj(ILI_TOPICB_ASSOC_EF1_E1, "REF").setobjectrefoid(OID3);
		iomLinkEF.addattrobj(ILI_TOPICB_ASSOC_EF1_F1, "REF").setobjectrefoid(OID2);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		List<Class> resolverClasses=new ArrayList<Class>();
		resolverClasses.add(ExternalObjResolverMock.class);
		settings.setTransientObject(Validator.CONFIG_OBJECT_RESOLVERS, resolverClasses);
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjF));
		validator.validate(new ObjectEvent(iomLinkEF));
		validator.validate(new EndBasketEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID2));
		validator.validate(new ObjectEvent(iomObjE));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(2,logger.getErrs().size());
		assertEquals("No object found with OID o3.", logger.getErrs().get(0).getEventMsg());
	}
	
	// Es wird getestet ob eine Fehlermeldung ausgegeben wird, wenn:
	// - Die role g1 (EXTERNAL true) der Association GH1 aufgerufen wird.
	// - Die target class G, innerhalb der basket nicht gefunden werden kann.
	// - Die target class in der Basket b2 existiert.
	@Test
	public void resolver_EmbeddedAsso_TargetOIDNotFound_Fail(){
		Iom_jObject iomObjG=new Iom_jObject(ILI_TOPICB_CLASSG, OID1);
		Iom_jObject iomObjH1=new Iom_jObject(ILI_TOPICB_CLASSH, OID2);
		iomObjH1.addattrobj("g1", "REF").setobjectrefoid(OID3);
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		List<Class> resolverClasses=new ArrayList<Class>();
		resolverClasses.add(ExternalObjResolverMock.class);
		settings.setTransientObject(Validator.CONFIG_OBJECT_RESOLVERS, resolverClasses);
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new StartTransferEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID1));
		validator.validate(new ObjectEvent(iomObjH1));
		validator.validate(new EndBasketEvent());
		validator.validate(new StartBasketEvent(ILI_TOPICB,BASKET_ID2));
		validator.validate(new ObjectEvent(iomObjG));
		validator.validate(new EndBasketEvent());
		validator.validate(new EndTransferEvent());
		// Asserts
		assertEquals(2,logger.getErrs().size());
		assertEquals("No object found with OID o3.", logger.getErrs().get(0).getEventMsg());
	}
}