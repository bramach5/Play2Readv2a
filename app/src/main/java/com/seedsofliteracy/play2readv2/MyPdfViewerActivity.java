package com.seedsofliteracy.play2readv2;

/**
 * Created by bramach5 on 9/23/2016.
 */

import com.sun.pdfview.action.PDFAction;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

public class MyPdfViewerActivity extends PdfViewerActivity {


        public int getPreviousPageImageResource() {
            return R.drawable.left_arrow;
        }

        public int getNextPageImageResource() {
            return R.drawable.right_arrow;
        }

        public int getZoomInImageResource() {
            return R.drawable.zoom_in;
        }

        public int getZoomOutImageResource() {
            return R.drawable.zoom_out;
        }

        public int getPdfPasswordLayoutResource() {
            return R.layout.pdf_file_password;
        }

        public int getPdfPageNumberResource() {
            return R.layout.dialog_pagenumber;
        }

        public int getPdfPasswordEditField() {
            return R.id.etPassword;
        }

        public int getPdfPasswordOkButton() {
            return R.id.btOK;
        }

        public int getPdfPasswordExitButton() {
            return R.id.btExit;
        }

        public int getPdfPageNumberEditField() {
            return R.id.pagenum_edit;
        }

    }
