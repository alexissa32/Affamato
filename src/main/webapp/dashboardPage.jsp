<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="java.util.*" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%
//this date is sometimes the next day -- working on a fix. - Julia
SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEEEE, MMMMMMMMM dd yyyy");
sdf.format(new Date());
String date = sdf.format(new Date());

int numTips = 80;
Random rand = new Random();
int rando = rand.nextInt(numTips);
String[] tips = new String[numTips];
	tips[0] = "Eggs can last way past the date on the egg carton! Here's how to test if they are still good: Fill a bowl with cold water and place eggs inside. If they're a few weeks old but still good to eat, they'll stand on one end at the bottom of the bowl. If they float to the surface, they're no longer fresh enough to eat.";
	tips[1] = "Set up the perfect workspace by gathering clean tools, bowls and utensils. And make sure to keep a trashcan within arm’s reach.";
	tips[2] = "Never overcrowd your skillet with food. The heat will not distribute evenly.";
	tips[3] = "To create an egg wash, whisk together a large egg with one tablespoon of water until smooth. Use as a glue to seal pastries, then brush on top for a glossy appearance.";
	tips[4] = "Peel tomatoes with ease! Cut an X in the top, and then simmer in a pot of hot water for 15 to 30 seconds. Cool down and the skin will fall right off.";
	tips[5] = "Get comfortable! Wear comfy clothes and an apron when you work in the kitchen and you won’t have to worry about getting dirty.";
	tips[6] = "Invest in a baking scale. Scales are not only an accurate way to measure your cooking ingredients, but they streamline the entire process.";
	tips[7]	= "Always read and re-read your recipes before you start cooking. Remember to clean as you go!";
	tips[8] = "Use two skewers instead of one when grilling or roasting to prevent your food from spinning.";
	tips[9] = "How to tell how well your steak is cooked: Hold your hand out, palm up. Poke the base of your hand by the base of the thumb. What does it feel like? If you guessed raw meat, you’re right. Now, make an OK sign with your hand by touching your forefinger and thumb together. Feel the same part of your hand. It’s a little firmer. This is how meat feels when it’s rare. You’re now going to move to your other fingers, and as you do, you’ll notice the pad of your hand will get progressively firmer. Touch your middle finger to the tip of your thumb. That’s how a medium rare steak feels. Next, touch the tip of your ring finger to your thumb. This is what a medium-well will feel like. Last but not least, touch your pinky to your thumb. That’s the equivalent of a well-done steak.";
    tips[10] = "To prevent butter from over-browning in your pan, add a little bit of lemon juice.";
    tips[11] = "Embrace salt. Don’t be afraid to use salt; it pulls the flavors out of your dishes. Cook with kosher salt and season with sea salt.";
    tips[12] = "No luck finding shallots? Replace with a combination of onions and garlic.";
    tips[13] = "After handling garlic, rub your fingers on stainless steel, like your sink, to get rid of the odor.";
    tips[14] = "Ovens can be liars! Place a second thermometer in your oven to ensure proper preheating temperatures.";
    tips[15] = "Ignore cooking times (but be reasonable). Check your dishes by using your own senses (smell, taste, touch) to decide when they are done.";
    tips[16] = "When poaching an egg, add a teaspoon of white vinegar to simmering water to help keep the yolk from breaking.";
    tips[17] = "For a great hardboiled egg every time, bring your pot to a boil and then turn off the stove. Let your eggs sit in the heated pot for 12 minutes and then transfer to cold water.";
    tips[18] = "Make an ideal sunny-side egg by covering your pan with a lid and letting the steam cook your egg. No flipping required.";
    tips[19] = "Anchor your cutting board to the counter with a damp paper towel to keep things steady and safe.";
    tips[20] = "Hold a knife properly: pinch the blade instead of gripping the handle.";
    tips[21] = "When sautéing garlic, use sliced garlic instead of minced to prevent burning.";
    tips[22] = "Invest in a seasoned cast iron skillet. This kitchen staple distributes heat evenly and is easy to clean.";
    tips[23] = "Remove tough stems on leafy greens by pinching the stem and gently pulling off the leaves with your other hand.";
    tips[24] = "If your recipe calls for buttermilk, you can use regular milk with lemon juice.";
    tips[25] = "Prepping salad before serving is a huge time saver. Layer all the ingredients in a bowl and don’t add the dressing until it's time to serve.";
    tips[26] = "Keep your spices away from sources of heat like the stove or lights. Herbs and spices can lose their flavor when exposed to humidity and heat.";
    tips[27] = "Save old, stale bread to make breadcrumbs in a food processor; you can freeze them for up to 6 months.";
    tips[28] = "Let steaks come to room temperature before seasoning and grilling.";
    tips[29] = "Store fresh herbs in a glass of water in your refrigerator.";
    tips[30] = "To prevent tears, cut off the root of the onion before you slice.";
    tips[31] = "For crispy fries or chips: slice the potato, then remove the starch by soaking in water for one hour before baking.";
    tips[32] = "Celery getting floppy? Try wrapping it in tin foil before storing in the refrigerator.";
    tips[33] = "Soften up hard brown sugar by placing a piece of dry bread in the bag overnight.";
    tips[34] = "Roll citrus on the counter using the palm of your hand to help release all of the juice pockets.";
    tips[35] = "Increase the shelf life of a halved avocado by keeping the pit intact and placing it in your refrigerator.";
    tips[36] = "To prevent sliced apples from browning, lightly squeeze lemon or lime juice on the pieces.";
    tips[37] = "You can store butter in the freezer for up to six months.";
    tips[38] = "Honey is a natural preservative and will never spoil. Don't let those expiration dates fool you!";
    tips[39] = "To last longer, opened flour bags can be stored in the freezer.";
    tips[40] = "Mushrooms should be kept dry, as they can easily soak and store water.";	
    tips[41] = "Use an egg slicer to cut small fruits like kiwis.";
    tips[42] = "Recipes are only a guideline. Feel free to substitute items that cater to your personal preferences.";
    tips[43] = "To rehydrate sun-dried tomatoes, soak them in hot water or stock for about 20 minutes.";
    tips[44] = "The basic ratio to make a classic vinaigrette is 3 parts oil to 1 part vinegar.";
    tips[45] = "To keep garlic from going rancid (yuck!), always store it at room temperature.";	
    tips[46] = "Keep knives sharp by using a sharpening tool frequently. A sharp knife is important for safety and efficiency.";
    tips[47] = "Purchasing and preparing a whole chicken is cost-effective and resourceful.";
    tips[48] = "Honey stuck in a jam? Place the container in hot water for about 5 minutes to loosen up the sticky residue.";
    tips[49] = "Safely chop odd-shaped vegetables by cutting off both ends for an even surface.";
    tips[50] = "Create simple syrup by simmering 1 cup of water and 1 cup of sugar in a medium heated pot until the sugar dissolves. Bottle and store in your refrigerator for up to 2 weeks.";
    tips[51] = "Freeze leftover tomato paste in small ice cube containers.";
    tips[52] = "To soften butter, cut slices into a bowl and let sit at room temperature for 10–15 minutes.";
    tips[53] = "When serving ice cream to large groups, ditch the ice cream scoop. Break open the whole container and slice the ice cream into portions.";		
    tips[54] = "If you need to grate soft cheeses, freeze the cheese for 30 minutes for a cleaner slice.";
    tips[55] = "A cutting tool called a mandolin can be your best friend. They allow you to perfectly julienne, slice and dice vegetables every time. Always slice slowly and use the safety guard to prevent you from cutting your finger.";
    tips[56] = "When sautéing, it is important to first heat the pan, then heat the oil, then add the ingredients.";
    tips[57] = "Moisturize dried coconut by adding a sprinkle of milk and letting it sit for 10 minutes.";
    tips[58] = "Prevent bacteria growth by cooling hot food in a shallow dish.";	
    tips[59] = "Make stock in large quantities and freeze in plastic bags for later use.";
    tips[60] = "Use Greek yogurt as a healthy substitute for mayo, sour cream, heavy cream and more.";	
    tips[61] = "Before baking, remove butter and eggs from the fridge and let them reach room temperature.";
    tips[62] = "Invest in high-quality extra virgin olive oil for special meals or to drizzle over dishes to accent flavors.";
    tips[63] = "Let cooked or grilled meat rest at room temperature before serving.";
    tips[64] = "Plunge vegetables in ice water after blanching to help maintain a bright color.";	
    tips[65] = "For easy clean-up, line baking sheets with parchment paper.";
    tips[66] = "Save money by purchasing in-season fruit and vegetables. You can freeze and store in airtight containers to save for later.";
    tips[67] = "Never over-season seafood; you want to still be able to taste the flavor of the fish. Simply use lemon juice, salt and pepper.";
    tips[68] = "Remember: to preserve flavor and prevent burning, it's important to always cook slow and keep your heat low.";
    tips[69] = "Always, always, always measure when baking. Baking is a science and any wrong measurements can be disastrous.";
    tips[70] = "Rice cookers can be your personal kitchen assistants. Let them do all the tedious work and you will never worry about monitoring and watching water boil.";
    tips[71] = "To make leafy greens last longer, wrap them in damp paper towels and place in a sealable plastic bag before storing.";
    tips[72] = "Test oil in a pan before adding all of your ingredients. Throw a small piece in and make sure it sizzles before adding the rest.";
    tips[73] = "When cooking with chili peppers, protect your hands and eyes by wearing rubber gloves. Or coat your hands in vegetable oil and wash them with soap and water immediately after handling.";
    tips[74] = "To prevent sogginess, do not dress salads for large parties. Serve, then allow guests to add their own dressing.";
    tips[75] = "Seafood should never smell overwhelmingly fishy; that's a sure sign that it’s starting to go bad.";
    tips[76] = "Chill cookie dough before putting it on a baking sheet. This will help prevent your butter from flattening and losing its fluffy texture.";
    tips[77] = "Remove seeds from chilies to help reduce heat.";
    tips[78] = "Overcooked vegetables lose important enzymes and nutrients.";
    tips[79] = "Disinfect wood cutting boards by hand washing with vinegar.";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>
</head>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
  <link type="text/css" rel="stylesheet" href="about.css" />
  
<body id="dashboardbody">
<%

    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
        pageContext.setAttribute("user", user);
%>
<div class="topnav">
  <a class="active" href="dashboardPage.jsp">My Dashboard</a>
  <a href="landingPage.jsp">Home</a>
  <a href="aboutPage.jsp">About</a>
  <a style="float:right" href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Log Out</a>
</div>
<div class="vertnav">
<br>
<l>
  <li><a class="active" href="dashboardPage.jsp">Welcome</a></li>
  <li><a href="inventoryPage.jsp">My Inventory</a></li>
  <li><a href="grocerylistPage.jsp">My Grocery List</a></li>
  <li><a href="recipesPage.jsp">My Recipes</a></li>
</l>
</div> 

<div class="container" style="width: 75%; float: right">
  <p style="float: middle; font-size: 30px; padding-top: 25pt"><%=date%></p>
	<div class="panel panel-danger">
	  <div class="panel-heading">Expiration Alerts!</div>
	  <div class="panel-body">List soon-to-be expiring ingredients and relevant dates here</div>
	</div>
	<div class="panel panel-info">
	  <div class="panel-heading">Tip of the Day</div>
	  <div class="panel-body"><%out.print("<p>" + tips[rando] + "</p>");%></div>

	</div>
</div>
<%
    } else {
    	response.sendRedirect("/landingPage.jsp");
    }
%>

</body>
</html>