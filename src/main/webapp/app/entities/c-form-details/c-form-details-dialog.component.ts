import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CFormDetails } from './c-form-details.model';
import { CFormDetailsPopupService } from './c-form-details-popup.service';
import { CFormDetailsService } from './c-form-details.service';

@Component({
    selector: 'jhi-c-form-details-dialog',
    templateUrl: './c-form-details-dialog.component.html'
})
export class CFormDetailsDialogComponent implements OnInit {

    cFormDetails: CFormDetails;
    isSaving: boolean;
    dateOfBirthDp: any;
    passportExpirationDateDp: any;
    dateOfArrivalInIndiaDp: any;
    visaEffectiveDateDp: any;
    visaExpiryDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private cFormDetailsService: CFormDetailsService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.cFormDetails.id !== undefined) {
            this.subscribeToSaveResponse(
                this.cFormDetailsService.update(this.cFormDetails));
        } else {
            this.subscribeToSaveResponse(
                this.cFormDetailsService.create(this.cFormDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<CFormDetails>) {
        result.subscribe((res: CFormDetails) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CFormDetails) {
        this.eventManager.broadcast({ name: 'cFormDetailsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-c-form-details-popup',
    template: ''
})
export class CFormDetailsPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cFormDetailsPopupService: CFormDetailsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.cFormDetailsPopupService
                    .open(CFormDetailsDialogComponent as Component, params['id']);
            } else {
                this.cFormDetailsPopupService
                    .open(CFormDetailsDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
