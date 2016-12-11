
package com.clj.blesample;

import android.R.integer;

import com.clj.APDU;
import com.clj.BleTest;
import com.clj.BleTest.IRecvCallback;
import com.clj.BleTest.ISendCallback;
import com.pacewear.tws.phoneside.wallet.step.IStep;
import com.pacewear.tws.phoneside.wallet.step.Step;

public class ApduStepModule {
    private enum APDU_STEP {
        selectCRS, selectaid, selectfs, selectMFs, money, cardnum,
    }

    private ISendCallback mSendCallback;
    private IUIUpdate mUpdate;
    private int mType = 0;

    public ApduStepModule(int type, ISendCallback callback, IUIUpdate uicallback) {
        mSendCallback = callback;
        mUpdate = uicallback;
        mType = type;
        mSelectStep.onEnterStep();
    }

    private IStep<APDU_STEP> mCurrentOrderStep = null;

    private abstract class ApduStep extends Step<APDU_STEP> {

        protected long mUniqueReq = -1;

        public ApduStep(APDU_STEP step) {
            super(step);
        }

        @Override
        protected boolean setStep(IStep<APDU_STEP> step) {
            return ApduStepModule.this.setStep(step);
        }

        @Override
        protected void notifyStepStatus(APDU_STEP step, STATUS status) {
        }
    }

    private final boolean setStep(IStep<APDU_STEP> step) {

        boolean handle = false;

        if (step == null) {
            return handle;
        }

        if (mCurrentOrderStep != step) {
            IStep<APDU_STEP> previousStep = mCurrentOrderStep;
            mCurrentOrderStep = step;
            if (previousStep != null) {
                previousStep.onQuitStep();
            }
            mCurrentOrderStep.onEnterStep();
            handle = true;
        }

        return handle;
    }

    private ApduStep mSelectCRSStep = new ApduStep(APDU_STEP.selectCRS) {

        @Override
        public void onStepHandle() {
            BleTest.sendDat(BleTest.TYPE_ICC, APDU.selectCRS(), mSendCallback, new IRecvCallback() {

                @Override
                public void onRecvSuc(String result) {
                    switchStep(mSelectStep);
                }

                @Override
                public void onRecvFail(int ret) {
                    keepStep();
                    mUpdate.onShowResult("fail");
                }
            });

        }

    };
    private ApduStep mSelectStep = new ApduStep(APDU_STEP.selectaid) {

        @Override
        public void onStepHandle() {
            BleTest.sendDat(BleTest.TYPE_ICC, APDU.selectAid(), mSendCallback, new IRecvCallback() {

                @Override
                public void onRecvSuc(String result) {
                    switchStep(mSelectFsStep);
                }

                @Override
                public void onRecvFail(int ret) {
                    keepStep();
                    mUpdate.onShowResult("fail");
                }
            });

        }

    };
    private ApduStep mSelectFsStep = new ApduStep(APDU_STEP.selectfs) {

        @Override
        public void onStepHandle() {
            BleTest.sendDat(BleTest.TYPE_ICC, APDU.selectFS(), mSendCallback, new IRecvCallback() {

                @Override
                public void onRecvSuc(String result) {
                    if (mType == 0) {
                        switchStep(mSelectMoneyAreaStep);
                    } else {
                        switchStep(mCardNumStep);
                    }

                }

                @Override
                public void onRecvFail(int ret) {
                    keepStep();
                    mUpdate.onShowResult("fail");
                }

            });

        }

    };
    private ApduStep mSelectMoneyAreaStep = new ApduStep(APDU_STEP.selectMFs) {

        @Override
        public void onStepHandle() {
            BleTest.sendDat(BleTest.TYPE_ICC, APDU.getCardMoneyArea(), mSendCallback,
                    new IRecvCallback() {

                @Override
                public void onRecvSuc(String result) {
                    switchStep(mCardMoneyStep);
                }

                @Override
                public void onRecvFail(int ret) {
                    keepStep();
                    mUpdate.onShowResult("fail");
                }

            });

        }

    };
    private ApduStep mCardMoneyStep = new ApduStep(APDU_STEP.money) {

        @Override
        public void onStepHandle() {
            BleTest.sendDat(BleTest.TYPE_ICC, APDU.getCardAmount(), mSendCallback,
                    new IRecvCallback() {

                @Override
                public void onRecvSuc(String result) {
                    mUpdate.onShowResult("余额：" + APDU.parseMoney(result) + "元");
                }

                @Override
                public void onRecvFail(int ret) {
                    keepStep();
                    mUpdate.onShowResult("fail");
                }

            });
        }
    };

    private ApduStep mCardNumStep = new ApduStep(APDU_STEP.cardnum) {

        @Override
        public void onStepHandle() {
            BleTest.sendDat(BleTest.TYPE_ICC, APDU.getCardNum(), mSendCallback,
                    new IRecvCallback() {

                @Override
                public void onRecvSuc(String result) {
                    // showResult("余额：" + APDU.parseMoney(result) + "分");
                    // if (mType == 0) {
                    // mUpdate.onShowResult("余额：" + APDU.parseMoney(result) + "元");
                    // } else {
                    mUpdate.onShowResult("卡号:" + APDU.parseCardNum(result));
                    // }
                }

                @Override
                public void onRecvFail(int ret) {
                    keepStep();
                    mUpdate.onShowResult("fail");
                }

            });

        }

    };
}
