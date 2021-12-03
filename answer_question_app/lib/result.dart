import 'package:answer_question_app/constant.dart';
import 'package:flutter/material.dart';

class Result extends StatelessWidget {
  final int resultScore;
  final Function resetHandler;

  Result(this.resultScore, this.resetHandler);

  String get resultPhrase {
    String resultText;
    if (resultScore <= 8) {
      resultText = Constant.YOU_ARE_AWESOME_AND_INNCOCENT;
    } else if (resultScore <= 12) {
      resultText = Constant.PRETTY_LIKEABLE;
    } else if (resultScore <= 16) {
      resultText = Constant.YOU_ARE_STRANGE;
    } else {
      resultText = Constant.YOU_ARE_SO_BAD;
    }
    return resultText;
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        children: <Widget>[
          Text(
            resultPhrase,
            style: TextStyle(fontSize: 36, fontWeight: FontWeight.bold),
            textAlign: TextAlign.center,
          ),
          ElevatedButton(
            child: Text(Constant.RESTART_QUIZ),
            onPressed: () => resetHandler(),
          ),
        ],
      ),
    );
  }
}
