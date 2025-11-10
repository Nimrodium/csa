# 1/3/8 Project
create a interactive story, i have already made something similar with NLE a while ago so i can take in concepts from that, 
in NLE i used first a registry system but later transitioned to storing the objects directly, for the java implemention, im unsure if i should go back to the registry.
also preferably it would be fun to make this loaded from a file. 
ironically, when i was writing NLE i was thinking of rewriting it in java in order to learn it, it seems like that will be coming true.

syntax for NLE was very rudimentary, this syntax can be more fluid, itll also help me get more comfortable with parsers. either that or i could just use a json file.

one issue is that i want custom functions in NLEj, in the custom parser, i could technically implement a whole basic language, but thats overkill.
the custom functions would be bound to checking conditions to unlock the node, and to run after entering the node,

possible syntax
```
# this is a comment
node(1) = {
	prompt: "Ah, so you're finally awake?"
	requires: ""
}
```



# Program Structure
The Program uses these objects to manage itself, the Node Registry, Node Object, and the Execution Manager. there also exists a static Node Defition Container

* Node Registry
> central store of node objects. this would be the static execution section of memory.
* Node Object
> represents one story presentation
* Execution Manager
> overseer of state, handles dispatch to new nodes.
* static Node Definition Container
> this represents the code which defines the actual game program which runs on NLEj
* AttributeSet
> similar to the windows registry, this is a root table of persistent data, a hashmap of objects which inherit the Attribute interface. can be a simple integer, string, or another attribute set.
> the NLEj loaded code uses the attribute set as its memory.

while i could write a parser and DSL for this program, that is frankly way too much work, im already doing too much. 
thus i think i will make this just act as a library which can be interfaced by the user in java or another jvm language.

while the assignment assumes single file, i will probably use true multifile java this time, just 2 files, the library file, and the public use of the library's api.

Node will have a functional building pipeline
``` java
NodeDefinitionContainer game = new NodeDefinitionContainer({
	new NodeBuilder().id(0).text("Ah so youre finally awake, whats your name?").answer(1,"my name is mr skyrim").answer(2,"i dont know what my name is...", new AttributeRequirement("amnesia")).build(),
	...
	}
);
```
