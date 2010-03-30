ANT task summary:

1. To build the plugin there are need some jars from the Intellij IDEA lib area.
Copy the following jars to the SQL-Code-Assistant-/sqlassistant-plugin9/idea-9-lib directory:

 annotations.jar
 extensions.jar
 idea.jar
 jdom.jar
 openapi.jar
 util.jar

2. Run the target task "build_plugin" to build the plugin's jar and
"bundle_plugin" to have all requried jars collected to the bundle ready for deployment.

3. Task "build_SYS_schema_package" allows to build a jar with serialized db objects (sys user).

4. Task "generate_plsql_parser" generates plsql parser using antlr grammar from directory grammars. 