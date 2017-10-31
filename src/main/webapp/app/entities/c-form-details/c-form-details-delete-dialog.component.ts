import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CFormDetails } from './c-form-details.model';
import { CFormDetailsPopupService } from './c-form-details-popup.service';
import { CFormDetailsService } from './c-form-details.service';

@Component({
    selector: 'jhi-c-form-details-delete-dialog',
    templateUrl: './c-form-details-delete-dialog.component.html'
})
export class CFormDetailsDeleteDialogComponent {

    cFormDetails: CFormDetails;

    constructor(
        private cFormDetailsService: CFormDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cFormDetailsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'cFormDetailsListModification',
                content: 'Deleted an cFormDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-c-form-details-delete-popup',
    template: ''
})
export class CFormDetailsDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cFormDetailsPopupService: CFormDetailsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.cFormDetailsPopupService
                .open(CFormDetailsDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
