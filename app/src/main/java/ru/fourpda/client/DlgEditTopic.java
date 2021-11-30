package ru.fourpda.client;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DlgEditTopic extends CustomDialog {
    private EditText f2552j = (EditText) this.rootView.findViewById(R.id.dlg_topic_title);
    private EditText f2553k = (EditText) this.rootView.findViewById(R.id.dlg_topic_description);
    private Widgets$CheckboxTextView f2554l = (Widgets$CheckboxTextView) this.rootView.findViewById(R.id.dlg_topic_poll);
    private TextView f2555m = (TextView) this.rootView.findViewById(R.id.dlg_topic_poll_header_caption);
    private EditText f2556n = (EditText) this.rootView.findViewById(R.id.dlg_topic_poll_header);
    private TextView f2557o = (TextView) this.rootView.findViewById(R.id.dlg_topic_poll_questions_caption);
    private LinearLayout f2558p = (LinearLayout) this.rootView.findViewById(R.id.dlg_topic_poll_questions);
    private View.OnClickListener f2559q;
    MainActivity f2560r;
    private int f2561s;
    private String f2562t;
    private String f2563u;
    private boolean f2564v;
    private boolean f2565w;
    private boolean f2566x;
    private String f2567y;
    private Document f2568z;

    public class View$OnClickListenerC0749a implements View.OnClickListener {
        View$OnClickListenerC0749a() {
        //    DlgEditTopic.this = r1;
        }

        @Override
        public void onClick(View view) {
            DlgEditTopic sVar = DlgEditTopic.this;
            sVar.f2566x = sVar.f2554l.getChecked();
            int i = 0;
            DlgEditTopic.this.f2555m.setVisibility(DlgEditTopic.this.f2566x ? 0 : 8);
            DlgEditTopic.this.f2556n.setVisibility(DlgEditTopic.this.f2566x ? 0 : 8);
            DlgEditTopic.this.f2557o.setVisibility(DlgEditTopic.this.f2566x ? 0 : 8);
            LinearLayout linearLayout = DlgEditTopic.this.f2558p;
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
            int indexOfChild = DlgEditTopic.this.f2558p.indexOfChild(view2);
            DlgEditTopic.this.f2558p.removeView(view2);
            if ((Integer) view2.getTag() == 1) {
                while (true) {
                    View childAt = DlgEditTopic.this.f2558p.getChildAt(indexOfChild);
                    if (childAt == null || (Integer) childAt.getTag() < 2) {
                        break;
                    }
                    DlgEditTopic.this.f2558p.removeViewAt(indexOfChild);
                }
            }
            while (indexOfChild >= 0) {
                View childAt2 = DlgEditTopic.this.f2558p.getChildAt(indexOfChild);
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
            int indexOfChild = DlgEditTopic.this.f2558p.indexOfChild(view);
            DlgEditTopic.this.m325u(indexOfChild, "Вопрос", false);
            DlgEditTopic.this.f2558p.getChildAt(indexOfChild).findViewById(R.id.question).requestFocus();
        }
    }

    public class View$OnClickListenerC0752d implements View.OnClickListener {
        View$OnClickListenerC0752d() {
       //     DlgEditTopic.this = r1;
        }

        @Override
        public void onClick(View view) {
            Document uVar;
            int i;
            String trim = DlgEditTopic.this.f2552j.getText().toString().trim();
            if (trim.length() == 0) {
                Toast.makeText(DlgEditTopic.this.f2560r, "Укажите заголовок темы", 1).show();
                DlgEditTopic.this.f2552j.requestFocus();
                return;
            }
            Document uVar2 = null;
            if (DlgEditTopic.this.f2565w) {
                Document uVar3 = new Document();
                if (DlgEditTopic.this.f2554l.getChecked()) {
                    String trim2 = DlgEditTopic.this.f2556n.getText().toString().trim();
                    if (trim2.length() == 0) {
                        Toast.makeText(DlgEditTopic.this.f2560r, "Укажите заголовок опроса", 1).show();
                        DlgEditTopic.this.f2556n.requestFocus();
                        return;
                    }
                    uVar3.append(trim2);
                    Document uVar4 = new Document();
                    uVar3.append(uVar4);
                    View view2 = null;
                    int i2 = 0;
                    while (i2 < DlgEditTopic.this.f2558p.getChildCount()) {
                        View childAt = DlgEditTopic.this.f2558p.getChildAt(i2);
                        Integer num = (Integer) childAt.getTag();
                        if (uVar2 == null || 3 == num.intValue() || uVar2.getDocument(2).count() != 0) {
                            Document uVar5 = uVar2;
                            if (1 == num.intValue()) {
                                Document uVar6 = new Document();
                                String trim3 = ((TextView) childAt.findViewById(R.id.question)).getText().toString().trim();
                                if (trim3.length() == 0) {
                                    Toast.makeText(DlgEditTopic.this.f2560r, "Укажите вопрос", 1).show();
                                    childAt.findViewById(R.id.question).requestFocus();
                                    return;
                                }
                                uVar6.append(trim3);
                                uVar6.append(Integer.valueOf(((Widgets$CheckboxView) childAt.findViewById(R.id.question_multi)).getChecked() ? 1 : 0));
                                uVar6.append(new Document());
                                uVar6.append(new Document());
                                uVar4.append(uVar6);
                                view2 = childAt;
                                uVar5 = uVar6;
                            }
                            if (3 == num.intValue()) {
                                String trim4 = ((TextView) childAt.findViewById(R.id.answer)).getText().toString().trim();
                                if (trim4.length() == 0) {
                                    Toast.makeText(DlgEditTopic.this.f2560r, "Укажите ответ", 1).show();
                                    childAt.findViewById(R.id.answer).requestFocus();
                                    return;
                                }
                                try {
                                    i = Integer.parseInt(((TextView) childAt.findViewById(R.id.answer_votes)).getText().toString());
                                } catch (Exception unused) {
                                    i = 0;
                                }
                                uVar5.getDocument(2).append(trim4);
                                uVar5.getDocument(3).append(Integer.valueOf(i));
                            }
                            i2++;
                            uVar2 = uVar5;
                        } else {
                            Toast.makeText(DlgEditTopic.this.f2560r, "Добавьте хотя бы один ответ", 1).show();
                            view2.findViewById(R.id.question).requestFocus();
                            return;
                        }
                    }
                    if (uVar4.count() == 0) {
                        Toast.makeText(DlgEditTopic.this.f2560r, "Добавьте хотя бы один вопрос", 1).show();
                        DlgEditTopic.this.f2556n.requestFocus();
                        return;
                    }
                }
                uVar = uVar3;
            } else {
                uVar = null;
            }
            DlgEditTopic sVar = DlgEditTopic.this;
            DocumentManager.getResultRequest(new TopicEditRequest(sVar.f2561s, trim, DlgEditTopic.this.f2553k.getText().toString().trim(), uVar));
        }
    }

    public class View$OnClickListenerC0753e implements View.OnClickListener {
        View$OnClickListenerC0753e() {
            //DlgEditTopic.this = r1;
        }

        @Override
        public void onClick(View view) {
            int indexOfChild = DlgEditTopic.this.f2558p.indexOfChild(view);
            DlgEditTopic.this.m326t(indexOfChild, "Ответ", 0);
            DlgEditTopic.this.f2558p.getChildAt(indexOfChild).findViewById(R.id.answer).requestFocus();
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
                Toast.makeText(DlgEditTopic.this.f2560r, "Топик отредактирован", 1).show();
                DlgEditTopic.this.f2560r.mainLayout.tab.page.tabReload();
            } else if (status == 4) {
                Toast.makeText(DlgEditTopic.this.f2560r, "Слишком много вопросов в опросе", 1).show();
            } else if (status == 5) {
                Toast.makeText(DlgEditTopic.this.f2560r, "Слишком много ответов в опросе", 1).show();
            } else {
                Toast.makeText(DlgEditTopic.this.f2560r, "Нет доступа", 1).show();
            }
        }
    }

    public DlgEditTopic(MainActivity mainActivity, int i, String str, String str2, boolean z, boolean z2, boolean z3, String str3, Document uVar) {
        super(mainActivity, mainActivity.getLayoutInflater().inflate(R.layout.dlg_topic_edit, (ViewGroup) null), null, null);
        this.f2560r = mainActivity;
        this.f2561s = i;
        this.f2562t = str;
        this.f2563u = str2;
        this.f2564v = z;
        this.f2565w = z2;
        this.f2566x = z3;
        this.f2567y = str3;
        this.f2568z = uVar;
        this.f2552j.setText(this.f2562t);
        this.f2552j.setEnabled(this.f2564v);
        this.f2553k.setText(this.f2563u);
        this.f2553k.setEnabled(this.f2564v);
        this.f2554l.setOnClickListener(new View$OnClickListenerC0749a());
        int i2 = 8;
        this.f2554l.setVisibility(this.f2565w ? 0 : 8);
        this.f2554l.setChecked(this.f2565w && this.f2566x);
        this.f2555m.setVisibility((!this.f2565w || !this.f2566x) ? 8 : 0);
        this.f2556n.setText(this.f2565w ? this.f2567y : "");
        this.f2556n.setVisibility((!this.f2565w || !this.f2566x) ? 8 : 0);
        this.f2557o.setVisibility((!this.f2565w || !this.f2566x) ? 8 : 0);
        LinearLayout linearLayout = this.f2558p;
        if (this.f2565w && this.f2566x) {
            i2 = 0;
        }
        linearLayout.setVisibility(i2);
        this.f2559q = new View$OnClickListenerC0750b();
        if (this.f2565w) {
            if (this.f2568z != null) {
                for (int i3 = 0; i3 < this.f2568z.count(); i3++) {
                    Document l = this.f2568z.getDocument(i3);
                    Document l2 = l.getDocument(2);
                    Document l3 = l.getDocument(3);
                    m325u(this.f2558p.getChildCount(), l.getString(0), l.getInt(1).intValue() > 0);
                    for (int i4 = 0; i4 < l2.count(); i4++) {
                        m326t(this.f2558p.getChildCount() - 1, l2.getString(i4), l3.getInt(i4).intValue());
                    }
                }
            }
            TextView textView = (TextView) this.f2560r.getLayoutInflater().inflate(R.layout.dlg_topic_edit_add_question, (ViewGroup) this.f2558p, false);
            this.f2558p.addView(textView);
            textView.setTag(0);
            textView.setOnClickListener(new View$OnClickListenerC0751c());
        }
        m620f(new View$OnClickListenerC0752d(), false);
    }

    public void m326t(int i, String str, int i2) {
        LinearLayout linearLayout = (LinearLayout) this.f2560r.getLayoutInflater().inflate(R.layout.dlg_topic_edit_answer, (ViewGroup) this.f2558p, false);
        ((EditText) linearLayout.findViewById(R.id.answer)).setText(str);
        EditText editText = (EditText) linearLayout.findViewById(R.id.answer_votes);
        editText.setText(Integer.valueOf(i2).toString());
        if (!this.f2564v) {
            editText.setVisibility(8);
        }
        linearLayout.findViewById(R.id.answer_delete).setOnClickListener(this.f2559q);
        linearLayout.setTag(3);
        this.f2558p.addView(linearLayout, i);
    }

    public void m325u(int i, String str, boolean z) {
        LinearLayout linearLayout = (LinearLayout) this.f2560r.getLayoutInflater().inflate(R.layout.dlg_topic_edit_question, (ViewGroup) this.f2558p, false);
        ((EditText) linearLayout.findViewById(R.id.question)).setText(str);
        ((Widgets$CheckboxView) linearLayout.findViewById(R.id.question_multi)).setChecked(z);
        linearLayout.findViewById(R.id.question_delete).setOnClickListener(this.f2559q);
        linearLayout.setTag(1);
        this.f2558p.addView(linearLayout, i);
        TextView textView = (TextView) this.f2560r.getLayoutInflater().inflate(R.layout.dlg_topic_edit_add_answer, (ViewGroup) this.f2558p, false);
        this.f2558p.addView(textView, i + 1);
        textView.setTag(2);
        textView.setOnClickListener(new View$OnClickListenerC0753e());
    }
}
