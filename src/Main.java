import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


class StringReplaceNode
{
	String tagStr;
	String toStr;
}


public class Main {

	//修正第一種語法
	//[CCShow action]
	//[CCSpawn actions:actFade, seq1, nil]
	//修正第二種語法
	//[CCScaleTo actionWithDuration:2.5f scale:1.05f];
	public static final String CLASS_NAME = "StringGame3";
	
	public static final String RES_OBJ_C_METHOD1 = "\\[\\s*[\\w:@()->]+\\s+\\w+(:\\s*[\\w:@()->\\\"]+(,\\s*[\\w:@()->\\\"]+)+)?\\s*\\]"; 
	public static final String RES_OBJ_C_METHOD2 = "\\[\\s*[\\w:@()->]+(\\s*[\\w@(): ,->\\\"]+:\\s*[\\w@(): ,->\\\"]+)+\\s*\\]"; 
	public static final String RES_OBJ_C_METHOD3 = "\\[\\s*\\w+\\.*\\w*\\s*\\]";
	
	
	
	public static void main(String arg[])
	{
//		String test1  = "[abc efg]";
//		String test2  = "[abc efg:12 andA:12]";
//		String test3  = "[abc efg:12 andA:12]";
//		String test4  = "[abc efg:12\nandA:12 andB:saxd]";
//		String test5  = "[adc efg:[CClayer layer:aaa]]";
//		String test6  = "[adc efg:[CClayer layer:[CCstring string:aaa]]]";
//		String test7  = "[adc efg:[CClayer layer:CCstring::string(aaa)]]";
//		String test8  = "[adc efg:[CClayer layer:aaa->string(aaa)]]";
//		String test9 = "actScale2= [CCScaleTo actionWithDuration:2.5f scale:1.05f];";
//		String test10 = "seqEnd	 = [CCSequence actions:[CCShow action], [CCSpawn actions:actFade, seq1, nil], nil];";
//		String test11 = "							[[SimpleAudioEngine sharedEngine] playBackgroundMusic:MUSIC_TIME loop:YES]";
//		String test12 = "sprite.visible = NO;";
//		String test13 = "id scaleBy1		= [CCScaleTo actionWithDuration:0.1f scale: 0.9f];";
//		String test14 = "id actCallFunc	= [CCCallFunc actionWithTarget:self selector:@selector(end)];";
//		String test15 = "[self schedule:@selector(changeTimer) interval:0.1f];";
//		String test16 = "CCSequence *seq1 = [CCSequence actions: scaleto, actionCallFunc, CCDelayTime, MoveTo, [CCDelayTime actionWithDuration:1.0f], last_actionCallFunc, [CCCallFuncND actionWithTarget:SuperChopsticksSprite selector:@selector(removeFromParentAndCleanup:) data:(void*)YES], nil];";
//		String test17 = "static	CGPoint	chopsticks_startLoc = { 371 , ( 121 - DEF_CHOPSTICKS_HEIGHT )*IPAD_ADJY };	// 筷子起始x、y座標";
//		String test18 = "iTag = [self getChildByRow:iCol];	\n"+
//		"if( iTag >= 0 )\n"+
//		"{\n"+
//		"	iRowSprite = (CCSprite*) [self getChildByTag:iTag];\n"+
//		"	\n"+
//		"	ChopsticksSprite.position = ccp( iRowSprite.position.x, ChopsticksSprite.position.y );\n"+
//		"	\n"+
//		"	[self vString_SetBgLightPos];\n"+
//		"	[self vString_ResetChopsticksObj];\n"+
//		"	// 人物也要動\n"+
//		"	[self vString_CharSetPosByChop];\n"+
//		"	[self vString_ChopsticksObjMove:TRUE];\n"+
//		"	return YES;\n"+
//		"}\n";
//		String test19 = "for( i = 0 ; i < Chopsticks.objectCnt ; i++ )\n"+
//		"{\n"+
//		"	if( Chopsticks.objectTable[ i ] != -1 )\n"+
//		"	{\n"+
//		"		CCSprite	*ObjSprite			= (CCSprite*) [self getChildByTag:Object[ Chopsticks.objectTable[ i ] ].iTag];\n"+
//		"		//[ObjSprite setScale:0.5f];\n"+
//		"		ObjSprite.position = ccp( ChopsticksSprite.position.x, ChopsticksSprite.position.y + DEF_M3_CHOPOBJECT_POSY - DEF_M3_CHOPOBJECT_SPACE*( Chopsticks.objectCnt - iIdx - 1 ) );\n"+
//		"		iIdx++;\n"+
//		"	}\n"+
//		"}";
//		String test20  = "CCSprite	*ObjSprite			= (CCSprite*) [self getChildByTag:Object[ Chopsticks.objectTable[ i ] ].iTag];";
////		String test21 = new String(test20);
////		test20 = test20.replaceFirst("\\[\\s*\\w+\\.*\\w*\\s*\\]", "aaa");//test20.replaceFirst(RES_OBJ_C_METHOD3, "xxx");
////		System.out.println("test20:" + test20);
////		test20 = test20.replaceFirst("\\[\\s*\\w+\\.*\\w*\\s*\\]", "bbb");//test20.replaceFirst(RES_OBJ_C_METHOD3, "xxx");
////		System.out.println("test20:" + test20);
//		
//		String test21 = 
//		"// 右上角\n"+
//		"rect = CGRectMake( APPWIDTH - 30, APPHEIGHT - 30,\n"+
//		"				  30, 30);\n"+
//		"if( CGRectContainsPoint( rect, location) )	\n"+
//		"{// 點到了\n"+
//		"	// 點一次加 combo 300\n"+
//		"	iComboCnt += 300;\n"+
//		"	if( iComboCnt > iMaxComboCnt )\n"+
//		"	{\n"+
//		"		iMaxComboCnt = iComboCnt;\n"+
//		"		[self GameComboReportAchievements];\n"+
//		"	}\n"+
//		"	[self vString_ShowCombo:180 iPosY:338];\n"+
//		"	return;\n"+
//		"}\n";
//
//		String test22=
//		"CCSprite *Sprite = (CCSprite*) [self getChildByTag:EN_TAG_CONTINUE];	\n"+
//		"if( !bGameEnd && CGRectContainsPoint(Sprite.boundingBox, location))\n"+
//		"{// 點到了\n"+
//		"	bGameStop = FALSE;\n"+
//		"	id scaleBy1 = [CCScaleTo actionWithDuration:0.1f scale: 0.9f];\n"+
//		"	id scaleBy2 = [CCScaleTo actionWithDuration:0.1f scale: 1.0f];\n"+
//		"	id actionCallFunc = [CCCallFunc actionWithTarget:self selector:@selector(vString_ResumeLayer)];\n"+
//		"	\n"+
//		"	[ Sprite runAction: [CCSequence actions: scaleBy1, scaleBy2, actionCallFunc, nil ]];		\n"+
//		"	[self vString_PlaySound:SOUND_BTN];	\n"+
//		"	self.isTouchEnabled = NO;\n"+
//		"	return;\n"+
//		"}";
//
//		String test23="[ Sprite runAction: CCSequence::actions( scaleBy1, scaleBy2, actionCallFunc, NULL )];";
//		
//		String test24 =
//			"// menu\n"+
//			"Sprite = (CCSprite*) [self getChildByTag:EN_TAG_MENU];	\n"+
//			"if( CGRectContainsPoint(Sprite.boundingBox, location))\n"+
//			"{// 點到了\n"+
//			"	bGameStop = FALSE;\n"+
//			"	\n"+
//			"	id scaleBy1 = [CCScaleTo actionWithDuration:0.1f scale: 0.9f];\n"+
//			"	id scaleBy2 = [CCScaleTo actionWithDuration:0.1f scale: 1.0f];\n"+
//			"	id actionCallFunc;\n"+
//			"	if( !data.bBuyFullGame ) {\n"+
//			"		actionCallFunc = [CCCallFunc actionWithTarget:self selector:@selector(vString_Buy)];\n"+
//			"	}\n"+
//			"	else {\n"+
//			"		actionCallFunc = [CCCallFunc actionWithTarget:self selector:@selector(vString_GobackMenu)];\n"+
//			"	}\n"+
//			"	\n"+
//			"	[ Sprite runAction: [CCSequence actions: scaleBy1, scaleBy2, actionCallFunc, nil ]];\n"+
//			"	[self vString_PlaySound:SOUND_BTN];	\n"+
//			"	self.isTouchEnabled = NO;\n"+
//			"	return;\n"+
//			"}";
//
//		
//		
//		objcMethodToCMethod(test1);
//		objcMethodToCMethod(test2);
//		objcMethodToCMethod(test3);
//		objcMethodToCMethod(test4);
//		objcMethodToCMethod(test5);
//		objcMethodToCMethod(test6);
//		objcMethodToCMethod(test7);
//		objcMethodToCMethod(test8);
//		objcMethodToCMethod(test9);
//		objcMethodToCMethod(test10);
//		objcMethodToCMethod(test11);
//		objcMethodToCMethod(test12);
//		objcMethodToCMethod(test13);
//		objcMethodToCMethod(test14);
//		objcMethodToCMethod(test15);
//		objcMethodToCMethod(test16);
//		objcMethodToCMethod(test17);
//		objcMethodToCMethod(test18);
//		objcMethodToCMethod(test19);
//		objcMethodToCMethod(test20);
//		objcMethodToCMethod(test21);
//		objcMethodToCMethod(test22);
//		objcMethodToCMethod(test23);
//		objcMethodToCMethod(test24);

		   new GUI();
		
		
	}

	public static String objcMethodToCMethod(String methodStr)
	{
		//System.out.println("ori:" + methodStr);
		String method1FixStr , method2FixStr;
		ArrayList<StringReplaceNode> aryHideList = new ArrayList<StringReplaceNode>();
		methodStr = fixObjcSimpleField(methodStr);
		methodStr = fixObjcSpecialStr(methodStr);
		methodStr = fixObjcAryHide(aryHideList, methodStr);
		while(true)
		{
			
			method1FixStr = fixObjcMethod1(methodStr);
			boolean isMethod1FixChange = !(method1FixStr.equals(methodStr));
			if(isMethod1FixChange)
			{
				methodStr = method1FixStr;
				continue;
			}
			
			method2FixStr = fixObjcMethod2(methodStr);
			boolean isMethod2FixChange = !(method2FixStr.equals(methodStr));
			
			if(isMethod2FixChange)
			{
				methodStr = method2FixStr;
				continue;
			}
			
			break;
			
		}
		methodStr = fixObjcAryShow(aryHideList, methodStr);
		//System.out.println("to: " + methodStr);
		return methodStr;
	}
	
	public static String fixObjcSimpleField(String methodStr)
	{
		methodStr = methodStr.replaceAll("YES", "true");
		methodStr = methodStr.replaceAll("NO", "false");
		methodStr = methodStr.replaceAll("TRUE", "true");
		methodStr = methodStr.replaceAll("FALSE", "false");
		methodStr = methodStr.replaceAll("nil", "NULL");
		methodStr = methodStr.replaceAll("BOOL", "bool");
		methodStr = methodStr.replaceAll("self", "this");
		return methodStr;
	}
	
	public static String fixObjcSpecialStr(String methodStr)
	{
		methodStr = methodStr.replaceAll("CGPoint", "CCPoint");
		methodStr = methodStr.replaceAll("CGRectContainsPoint", 
										 "CCRect::CGRectContainsPoint");
		methodStr = methodStr.replaceAll("CGRectMake", "CCRectMake");
		methodStr = methodStr.replaceAll("@\\\"", "\\\"");     
		
		Pattern pattern = Pattern.compile("\\.anchorPoint\\s*=\\s*.+;");
		Matcher matcher = pattern.matcher(methodStr);
		while(matcher.find())
		{
			int end = matcher.end()+1;
			methodStr = methodStr.replaceFirst("\\.anchorPoint\\s*=\\s*", "->setAnchorPoint(");
			//methodStr = methodStr.replaceFirst(";", ");");
			methodStr = methodStr.substring(0, end) +")" +  methodStr.substring(end, methodStr.length());
			matcher.reset(methodStr);
		}
		
		pattern = Pattern.compile("\\.position\\s*=\\s*.+;");
		matcher = pattern.matcher(methodStr);
		while(matcher.find())
		{
			int end = matcher.end()+1;
			methodStr = methodStr.replaceFirst("\\.position\\s*=\\s*", "->setPosition(");
			//methodStr = methodStr.replaceFirst(";", ");");
			methodStr = methodStr.substring(0, end) +")" +  methodStr.substring(end, methodStr.length());
			matcher.reset(methodStr);
		}
		
		pattern = Pattern.compile("\\.scale\\s*=\\s*.+;");
		matcher = pattern.matcher(methodStr);
		while(matcher.find())
		{
			int end = matcher.end()+1;
			methodStr = methodStr.replaceFirst("\\.scale\\s*=\\s*", "->setScale(");
			//methodStr = methodStr.replaceFirst(";", ");");
			methodStr = methodStr.substring(0, end) +")" +  methodStr.substring(end, methodStr.length());
			matcher.reset(methodStr);
		}
		
		pattern = Pattern.compile("id\\s+\\w+\\s+=");
		matcher = pattern.matcher(methodStr);
		while(matcher.find())
		{
			System.out.println("find id");

			String tempSubStr = methodStr.substring(matcher.start(), matcher.end());
			tempSubStr = tempSubStr.replaceFirst("id\\s+", "CCFiniteTimeAction *");
			methodStr = methodStr.substring(0, matcher.start()) + tempSubStr + methodStr.substring( matcher.end(), methodStr.length());
			matcher.reset(methodStr);
		}
		
		
		return methodStr;
	}
	
	public static String fixObjcAryHide(ArrayList<StringReplaceNode> fixObjcAryShow, String methodStr)
	{
		Pattern pattern = Pattern.compile(RES_OBJ_C_METHOD3);
		Matcher matcher = pattern.matcher(methodStr);
		
		int idx = 1;
		while(matcher.find())
		{
			String subStr = methodStr.substring(matcher.start(), matcher.end());
			String tagStr = "ary__Hide__"+idx;
			methodStr = methodStr.replace(subStr, tagStr);
			StringReplaceNode node = new StringReplaceNode();
			node.tagStr = tagStr;
			node.toStr = subStr;
			fixObjcAryShow.add(node);
			idx++;
			matcher.reset(methodStr);
		}
		return methodStr;
		
	}
	
	public static String fixObjcAryShow(ArrayList<StringReplaceNode> aryHideStr, String methodStr)
	{
		for(int i=aryHideStr.size()-1; i>=0; i--)
		{
			StringReplaceNode node = aryHideStr.get(i);
			//System.out.println(node.tagStr+","+node.toStr);
			methodStr = methodStr.replace(node.tagStr, node.toStr);
		}
		aryHideStr.clear();
		return methodStr;
	}
	
	public static String fixObjcMethod1(String methodStr)
	{
		Pattern pattern = Pattern.compile(RES_OBJ_C_METHOD1);
		Matcher matcher = pattern.matcher(methodStr);
		
		//修正第一種語法
		//[CCShow action]
		//[CCSpawn actions:actFade, seq1, nil]
		while(matcher.find())
		{
			int start = matcher.start();
			int end = matcher.end();
			//System.out.println("1, " + start + "," + end);
			
			String subStr = methodStr.substring(start, end);
			String subReplaceStr = fixObjcToCMethod(subStr);
			
			methodStr = methodStr.replace(subStr, subReplaceStr);
			matcher.reset(methodStr);
			
		}
		//System.out.println("1, " + methodStr);
		return methodStr;
	}
	
	public static String fixObjcMethod2(String methodStr)
	{
		//修正第二種語法
		//[CCScaleTo actionWithDuration:2.5f scale:1.05f];
		Pattern pattern = Pattern.compile(RES_OBJ_C_METHOD2);
		Matcher matcher = pattern.matcher(methodStr);
		
		while(matcher.find())
		{
			int start = matcher.start();
			int end = matcher.end();
			//System.out.println("2, " + start + "," + end);
			
			String subStr = methodStr.substring(start, end);
			String subReplaceStr = new String(subStr);

			subReplaceStr = subReplaceStr.replaceFirst("::", "\\^");
			subReplaceStr = fixObjcToCMethod(subReplaceStr);
			
			
			while(true )
			{
				String tempStr = subReplaceStr.replaceFirst("\\s+\\w*:", ", ");
				if(subReplaceStr.equals(tempStr))
				{
					break;
				}else{
					subReplaceStr = tempStr;
				}
			}
			subReplaceStr = subReplaceStr.replaceFirst("\\^", "::");
			methodStr = methodStr.replace(subStr, subReplaceStr);
			matcher.reset(methodStr);
		}
		//System.out.println("2, " + methodStr);
		return methodStr;
	}
	
	public static String fixObjcToCMethod(String str)
	{
		String subReplaceStr = new String(str);
		String classData = subReplaceStr.substring(subReplaceStr.indexOf("[")+1, subReplaceStr.indexOf(" "));
		boolean isCCClass = checkIsCCClass(classData);
		if(subReplaceStr.indexOf(':') != -1)
		{
			subReplaceStr = subReplaceStr.replaceFirst(":", "(");
			subReplaceStr = subReplaceStr.replaceFirst("\\]", ")");
		}else
		{
			subReplaceStr = subReplaceStr.replaceFirst("\\]", "()");
		}
		subReplaceStr = subReplaceStr.replaceFirst("\\[", "");
		int classIdx = str.indexOf(classData);
		
		int whiteIdx = subReplaceStr.indexOf(" ", classIdx+1);
		subReplaceStr = subReplaceStr.substring(0, whiteIdx) + 
						(isCCClass?"::":"->")+
						subReplaceStr.substring(whiteIdx+1, subReplaceStr.length());
			
		
//		System.out.println("subReplaceStr, " + subReplaceStr);
		if(subReplaceStr.matches(".*@selector\\([\\w:]+\\).*"))
		{
//			System.out.println("has selector " + classData);
			subReplaceStr = fixObjcSelector(classData, subReplaceStr);
		}
		
		return subReplaceStr;
	}
	
	public static boolean checkIsCCClass(String classData)
	{
		boolean isCCClass = false;
		if(classData.equals("SimpleAudioEngine"))    isCCClass = true;
		if(classData.matches("CC[A-Z]+\\w+"))    isCCClass = true;
		return isCCClass;
	}
	
	public static String fixObjcSelector(String classStr, String fixStr)
	{
		if(classStr.equals("this"))    
			fixStr = fixStr.replaceFirst("@selector\\(", "schedule_selector(" + CLASS_NAME + "::");
		if(classStr.equals("CCCallFunc"))    
			fixStr = fixStr.replaceFirst("@selector\\(", "callfunc_selector(" + CLASS_NAME + "::");
		if(classStr.equals("CCCallFuncND"))    
			fixStr = fixStr.replaceFirst("@selector\\(", "callfuncND_selector(" + CLASS_NAME + "::");
		return fixStr;
	}
}
