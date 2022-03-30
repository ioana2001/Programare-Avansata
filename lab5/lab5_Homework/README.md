
Homework (2p)
<ul>
<li>Represent the commands using classes instead of methods. Use an interface or an abstract class in order to desribe a generic command.</li>
<li>Implement the commands load, list, view, report (create the classes AddCommand, ListCommand, etc.).</li>
<ul>
<li>list: prints the list of items on the screen;</li>
<li>view: opens an item using the native operating system application (see the Desktop class);</li>
<li>report: creates (and opens) an HTML report representing the content of the catalog.</li>
<li>Use a template engine such as FreeMarker or Velocity, in order to create the HTML report.</li>
<li>(+1p) Use Apache Tika in order to extract metadata from your catalog items and implement the command info in order to display them.</li>
</ul>
<li>The application will signal invalid date or the commands that are not valid using custom exceptions.</li>
<li>The final form of the application will be an executable JAR archive. Identify the generated archive and launch the application from the console, using the JAR.</li>
</ul>
