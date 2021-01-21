import 'package:flutter/material.dart';
import 'package:hive/hive.dart';
import 'package:hive_demo/model/monster.dart';
import 'package:hive_flutter/hive_flutter.dart';

class MainPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Hive Database"),
      ),
      body: FutureBuilder(
        future: Hive.openBox("monsters"),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            if (snapshot.hasError) {
              return Center(
                child: Text(snapshot.error.toString()),
              );
            } else {
              var monstersBox = Hive.box("monsters");
              if (monstersBox.length == 0) {
                monstersBox.add(
                  Monster(name: "Pepaya", level: 1),
                );
                monstersBox.add(
                  Monster(name: "Mangga", level: 2),
                );
                monstersBox.add(
                  Monster(name: "Semangka", level: 3),
                );
              }
              return WatchBoxBuilder(
                box: monstersBox,
                builder: (context, monsters) => Container(
                  margin: EdgeInsets.all(15),
                  child: ListView.builder(
                    itemCount: monsters.length,
                    itemBuilder: (context, index) {
                      Monster monster = monsters.getAt(index);
                      return Container(
                        margin: EdgeInsets.all(10),
                        padding: EdgeInsets.all(10),
                        decoration: BoxDecoration(
                          color: Colors.white,
                          boxShadow: [
                            BoxShadow(
                              color: Colors.black.withOpacity(0.5),
                              blurRadius: 3,
                              offset: Offset(2, 2),
                            )
                          ],
                        ),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              monster.name +
                                  " [" +
                                  monster.level.toString() +
                                  "]",
                            ),
                            Row(
                              children: <Widget>[
                                IconButton(
                                  icon: Icon(Icons.edit),
                                  onPressed: () {
                                    monsters.putAt(
                                      index,
                                      Monster(
                                          name: monster.name,
                                          level: monster.level + 1),
                                    );
                                  },
                                  color: Colors.grey,
                                ),
                                IconButton(
                                  icon: Icon(Icons.content_copy),
                                  onPressed: () {
                                    monsters.add(Monster(
                                        name: monster.name,
                                        level: monster.level));
                                  },
                                  color: Colors.green,
                                ),
                                IconButton(
                                  icon: Icon(Icons.delete),
                                  onPressed: () {
                                    monsters.deleteAt(index);
                                  },
                                  color: Colors.red,
                                ),
                              ],
                            )
                          ],
                        ),
                      );
                    },
                  ),
                ),
              );
            }
          } else {
            return Center(
              child: CircularProgressIndicator(),
            );
          }
        },
      ),
    );
  }
}
