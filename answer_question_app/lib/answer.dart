import 'package:flutter/material.dart';

class Answer extends StatelessWidget {
  final Function selectHandler;
  final String answerText;

  Answer(this.selectHandler, this.answerText);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      child: ElevatedButton(
        style: ButtonStyle(
            backgroundColor: MaterialStateProperty.all(Colors.red),
            padding: MaterialStateProperty.all(EdgeInsets.all(20)),
            textStyle: MaterialStateProperty.all(TextStyle(fontSize: 13))),
        child: Text(answerText),
        onPressed: () => selectHandler(),
      ),
    );
  }
}
