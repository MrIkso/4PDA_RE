package ru.fourpda.client;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DlgEditTopic extends CustomDialog {
    private EditText dlgTopicTitle = (EditText) this.rootView.findViewById(R.id.dlg_topic_title);
    private EditText dlgTopicDescription = (EditText) this.rootView.findViewById(R.id.dlg_topic_description);
    private Widgets$CheckboxTextView topicPool = (Widgets$CheckboxTextView) this.rootView.findViewById(R.id.dlg_topic_poll);
    private TextView topicPoolHeaderCaption = (TextView) this.rootView.findViewById(R.id.dlg_topic_poll_header_caption);
    private EditText topicPoolHeader = (EditText) this.rootView.findViewById(R.id.dlg_topic_poll_header);
    private TextView dlgTopicPollQuestionsCaption = (TextView) this.rootView.findViewById(R.id.dlg_topic_poll_questions_caption);
    private LinearLayout topicPollQuestions = (LinearLayout) this.rootView.findViewById(R.id.dlg_topic_poll_questions);
    private View.OnClickListener answerDeleteOnClickListener;
    MainActivity mainActivity;
    private int topicId;
    private String topicTitle;
    private String topicDescText;
    private boolean f2564v;
    private boolean f2565w;
    private boolean f2566x;
    private String topicHeaderText;
    private Document rootTopicDocument;

    public class View$OnClickListenerC0749a implements View.OnClickListener {
        View$OnClickListenerC0749a() {
        //    DlgEditTopic.this = r1;
        }

        @Override
        public void onClick(View view) {
            DlgEditTopic sVar = DlgEditTopic.this;
            sVar.f2566x = sVar.topicPool.getChecked();
            int i = 0;
            DlgEditTopic.this.topicPoolHeaderCaption.setVisibility(DlgEditTopic.this.f2566x ? 0 : 8);
            DlgEditTopic.this.topicPoolHeader.setVisibility(DlgEditTopic.this.f2566x ? 0 : 8);
            DlgEditTopic.this.dlgTopicPollQuestionsCaption.setVisibility(DlgEditTopic.this.f2566x ? 0 : 8);
            LinearLayout linearLayout = DlgEditTopic.this.topicPollQuestions;
            if (!DlgEditTopic.this.f2566x) {
                i = 8;
            }
            linearLayout.setVisibility(i);
        }
    }

    public class View$OnClickListenerC0750b implements View.OnClickListener {
        View$OnClickListenerC0750b() {
       //     DlgEditTopic.this = r1;
        }

        @Override
        public void onClick(View view) {
            View view2 = (View) view.getParent();
            int indexOfChild = DlgEditTopic.this.topicPollQuestions.indexOfChild(view2);
            DlgEditTopic.this.topicPollQuestions.removeView(view2);
            if ((Integer) view2.getTag() == 1) {
                while (true) {
                    View childAt = DlgEditTopic.this.topicPollQuestions.getChildAt(indexOfChild);
                    if (childAt == null || (Integer) childAt.getTag() < 2) {
                        break;
                    }
                    DlgEditTopic.this.topicPollQuestions.removeViewAt(indexOfChild);
                }
            }
            while (indexOfChild >= 0) {
                View childAt2 = DlgEditTopic.this.topicPollQuestions.getChildAt(indexOfChild);
                if (childAt2 == null || (((Integer) childAt2.getTag()).intValue() & 1) <= 0) {
                    indexOfChild--;
                } else {
                    childAt2.findViewById(((Integer) childAt2.getTag()).intValue() == 1 ? R.id.question : R.id.answer).requestFocus();
                    return;
                }
            }
        }
    }

    public class View$OnClickListenerC0751c implements View.OnClickListener {
        View$OnClickListenerC0751c() {
       //     DlgEditTopic.this = r1;
        }

        @Override
        public void onClick(View view) {
            int indexOfChild = DlgEditTopic.this.topicPollQuestions.indexOfChild(view);
            DlgEditTopic.this.addQuestion(indexOfChild, "Вопрос", false);
            DlgEditTopic.this.topicPollQuestions.getChildAt(indexOfChild).findViewById(R.id.question).requestFocus();
        }
    }

    public class View$OnClickListenerC0752d implements View.OnClickListener {
        View$OnClickListenerC0752d() {
       //     DlgEditTopic.this = r1;
        }

        @Override
        public void onClick(View view) {
            Document topicDocument;
            int answerVotes;
            String title = DlgEditTopic.this.dlgTopicTitle.getText().toString().trim();
            if (title.length() == 0) {
                Toast.makeText(DlgEditTopic.this.mainActivity, "Укажите заголовок темы", 1).show();
                DlgEditTopic.this.dlgTopicTitle.requestFocus();
                return;
            }
            Document uVar2 = null;
            if (DlgEditTopic.this.f2565w) {
                Document uVar3 = new Document();
                if (DlgEditTopic.this.topicPool.getChecked()) {
                    String topicPoolHeader = DlgEditTopic.this.topicPoolHeader.getText().toString().trim();
                    if (topicPoolHeader.length() == 0) {
                        Toast.makeText(DlgEditTopic.this.mainActivity, "Укажите заголовок опроса", 1).show();
                        DlgEditTopic.this.topicPoolHeader.requestFocus();
                        return;
                    }
                    uVar3.append(topicPoolHeader);
                    Document uVar4 = new Document();
                    uVar3.append(uVar4);
                    View view2 = null;
                    int i2 = 0;
                    while (i2 < DlgEditTopic.this.topicPollQuestions.getChildCount()) {
                        View childAt = DlgEditTopic.this.topicPollQuestions.getChildAt(i2);
                        Integer num = (Integer) childAt.getTag();
                        if (uVar2 == null || 3 == num.intValue() || uVar2.getDocument(2).count() != 0) {
                            Document rootQuestionDocument = uVar2;
                            if (1 == num.intValue()) {
                                Document questionDocument = new Document();
                                String question = ((TextView) childAt.findViewById(R.id.question)).getText().toString().trim();
                                if (question.length() == 0) {
                                    Toast.makeText(DlgEditTopic.this.mainActivity, "Укажите вопрос", 1).show();
                                    childAt.findViewById(R.id.question).requestFocus();
                                    return;
                                }
                                questionDocument.append(question);
                                questionDocument.append(Integer.valueOf(((Widgets$CheckboxView) childAt.findViewById(R.id.question_multi)).getChecked() ? 1 : 0));
                                questionDocument.append(new Document());
                                questionDocument.append(new Document());
                                uVar4.append(questionDocument);
                                view2 = childAt;
                                rootQuestionDocument = questionDocument;
                            }
                            if (3 == num.intValue()) {
                                String answer = ((TextView) childAt.findViewById(R.id.answer)).getText().toString().trim();
                                if (answer.length() == 0) {
                                    Toast.makeText(DlgEditTopic.this.mainActivity, "Укажите ответ", 1).show();
                                    childAt.findViewById(R.id.answer).requestFocus();
                                    return;
                                }
                                try {
                                    answerVotes = Integer.parseInt(((TextView) childAt.findViewById(R.id.answer_votes)).getText().toString());
                                } catch (Exception unused) {
                                    answerVotes = 0;
                                }
                                rootQuestionDocument.getDocument(2).append(answer);
                                rootQuestionDocument.getDocument(3).append(Integer.valueOf(answerVotes));
                            }
                            i2++;
                            uVar2 = rootQuestionDocument;
                        } else {
                            Toast.makeText(DlgEditTopic.this.mainActivity, "Добавьте хотя бы один ответ", 1).show();
                            view2.findViewById(R.id.question).requestFocus();
                            return;
                        }
                    }
                    if (uVar4.count() == 0) {
                        Toast.makeText(DlgEditTopic.this.mainActivity, "Добавьте хотя бы один вопрос", 1).show();
                        DlgEditTopic.this.topicPoolHeader.requestFocus();
                        return;
                    }
                }
                topicDocument = uVar3;
            } else {
                topicDocument = null;
            }
            DlgEditTopic sVar = DlgEditTopic.this;
            DocumentManager.getResultRequest(new TopicEditRequest(sVar.topicId, title, DlgEditTopic.this.dlgTopicDescription.getText().toString().trim(), topicDocument));
        }
    }

    public class View$OnClickListenerC0753e implements View.OnClickListener {
        View$OnClickListenerC0753e() {
            //DlgEditTopic.this = r1;
        }

        @Override
        public void onClick(View view) {
            int indexOfChild = DlgEditTopic.this.topicPollQuestions.indexOfChild(view);
            DlgEditTopic.this.addQuestion(indexOfChild, "Ответ", 0);
            DlgEditTopic.this.topicPollQuestions.getChildAt(indexOfChild).findViewById(R.id.answer).requestFocus();
        }
    }

    private class TopicEditRequest extends API.ForumTopicEditRequest {
        TopicEditRequest(int topicId, String title, String descriptions, Document document) {
            super(0, topicId, title, descriptions, document);
         //   DlgEditTopic.this = r7;
            this.statusMessage = "Сохранение топика";
        }

        @Override
        public void prepareResult(int status, Document uVar) {
            if (status == 0) {
                DlgEditTopic.this.dialog.dismiss();
                Toast.makeText(DlgEditTopic.this.mainActivity, "Топик отредактирован", 1).show();
                DlgEditTopic.this.mainActivity.mainLayout.tab.page.tabReload();
            } else if (status == 4) {
                Toast.makeText(DlgEditTopic.this.mainActivity, "Слишком много вопросов в опросе", 1).show();
            } else if (status == 5) {
                Toast.makeText(DlgEditTopic.this.mainActivity, "Слишком много ответов в опросе", 1).show();
            } else {
                Toast.makeText(DlgEditTopic.this.mainActivity, "Нет доступа", 1).show();
            }
        }
    }

    public DlgEditTopic(MainActivity mainActivity, int topicId, String topicTitle, String topicDescription, boolean z, boolean z2, boolean z3, String topicPoolHeader, Document topicDocument) {
        super(mainActivity, mainActivity.getLayoutInflater().inflate(R.layout.dlg_topic_edit, (ViewGroup) null), null, null);
        this.mainActivity = mainActivity;
        this.topicId = topicId;
        this.topicTitle = topicTitle;
        this.topicDescText = topicDescription;
        this.f2564v = z;
        this.f2565w = z2;
        this.f2566x = z3;
        this.topicHeaderText = topicPoolHeader;
        this.rootTopicDocument = topicDocument;
        this.dlgTopicTitle.setText(this.topicTitle);
        this.dlgTopicTitle.setEnabled(this.f2564v);
        this.dlgTopicDescription.setText(this.topicDescText);
        this.dlgTopicDescription.setEnabled(this.f2564v);
        this.topicPool.setOnClickListener(new View$OnClickListenerC0749a());
        int i2 = 8;
        this.topicPool.setVisibility(this.f2565w ? 0 : 8);
        this.topicPool.setChecked(this.f2565w && this.f2566x);
        this.topicPoolHeaderCaption.setVisibility((!this.f2565w || !this.f2566x) ? 8 : 0);
        this.topicPoolHeader.setText(this.f2565w ? this.topicHeaderText : "");
        this.topicPoolHeader.setVisibility((!this.f2565w || !this.f2566x) ? 8 : 0);
        this.dlgTopicPollQuestionsCaption.setVisibility((!this.f2565w || !this.f2566x) ? 8 : 0);
        LinearLayout linearLayout = this.topicPollQuestions;
        if (this.f2565w && this.f2566x) {
            i2 = 0;
        }
        linearLayout.setVisibility(i2);
        this.answerDeleteOnClickListener = new View$OnClickListenerC0750b();
        if (this.f2565w) {
            if (this.rootTopicDocument != null) {
                for (int i3 = 0; i3 < this.rootTopicDocument.count(); i3++) {
                    Document questionTitles = this.rootTopicDocument.getDocument(i3);
                    Document qestionsList = questionTitles.getDocument(2);
                    Document questionAnsvers = questionTitles.getDocument(3);
                    addQuestion(this.topicPollQuestions.getChildCount(), questionTitles.getString(0), questionTitles.getInt(1).intValue() > 0);
                    for (int i4 = 0; i4 < qestionsList.count(); i4++) {
                        addQuestion(this.topicPollQuestions.getChildCount() - 1, qestionsList.getString(i4), questionAnsvers.getInt(i4).intValue());
                    }
                }
            }
            TextView textView = (TextView) this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_topic_edit_add_question, (ViewGroup) this.topicPollQuestions, false);
            this.topicPollQuestions.addView(textView);
            textView.setTag(0);
            textView.setOnClickListener(new View$OnClickListenerC0751c());
        }
        m620f(new View$OnClickListenerC0752d(), false);
    }

    public void addQuestion(int index, String answer, int votes) {
        LinearLayout answerDelete = (LinearLayout) this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_topic_edit_answer, (ViewGroup) this.topicPollQuestions, false);
        ((EditText) answerDelete.findViewById(R.id.answer)).setText(answer);
        EditText answerVotes = (EditText) answerDelete.findViewById(R.id.answer_votes);
        answerVotes.setText(Integer.valueOf(votes).toString());
        if (!this.f2564v) {
            answerVotes.setVisibility(8);
        }
        answerDelete.findViewById(R.id.answer_delete).setOnClickListener(this.answerDeleteOnClickListener);
        answerDelete.setTag(3);
        this.topicPollQuestions.addView(answerDelete, index);
    }

    public void addQuestion(int index, String answer, boolean isQuestionMulti) {
        LinearLayout linearLayout = (LinearLayout) this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_topic_edit_question, (ViewGroup) this.topicPollQuestions, false);
        ((EditText) linearLayout.findViewById(R.id.question)).setText(answer);
        ((Widgets$CheckboxView) linearLayout.findViewById(R.id.question_multi)).setChecked(isQuestionMulti);
        linearLayout.findViewById(R.id.question_delete).setOnClickListener(this.answerDeleteOnClickListener);
        linearLayout.setTag(1);
        this.topicPollQuestions.addView(linearLayout, index);
        TextView textView = (TextView) this.mainActivity.getLayoutInflater().inflate(R.layout.dlg_topic_edit_add_answer, (ViewGroup) this.topicPollQuestions, false);
        this.topicPollQuestions.addView(textView, index + 1);
        textView.setTag(2);
        textView.setOnClickListener(new View$OnClickListenerC0753e());
    }
}
