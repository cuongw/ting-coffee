import 'package:flutter/material.dart';

const Color primaryColor = Color.fromARGB(255, 228, 249, 245);
const Color accentColor = Color.fromARGB(255, 0, 99, 65);
const Color fontColor = Color.fromARGB(255, 38, 54, 70);
const Color fontColorLight = Color.fromARGB(180, 38, 54, 70);

/* Dialog */
const TextStyle titleStyle =  TextStyle(
  color: accentColor,
  fontFamily: 'Dosis',
  fontSize: 19.0,
  fontWeight: FontWeight.w600
);
const TextStyle errorTitleStyle =  TextStyle(
  color: Colors.redAccent,
  fontFamily: 'Dosis',
  fontSize: 19.0,
  fontWeight: FontWeight.w600
);
const TextStyle contentStyle =  TextStyle(
  color: fontColor, 
  fontFamily: 'Dosis', 
  fontSize: 16.0,
  fontWeight: FontWeight.w500
);
const TextStyle okButtonStyle =  TextStyle(
  color: Colors.blueAccent, 
  fontFamily: 'Dosis', 
  fontSize: 16.0,
  fontWeight: FontWeight.w600
);
const TextStyle cancelButtonStyle =  TextStyle(
  color: Colors.redAccent, 
  fontFamily: 'Dosis', 
  fontSize: 16.0,
  fontWeight: FontWeight.w600
);

