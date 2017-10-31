import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SamplePmsSharedModule } from '../../shared';
import {
    CFormDetailsService,
    CFormDetailsPopupService,
    CFormDetailsComponent,
    CFormDetailsDetailComponent,
    CFormDetailsDialogComponent,
    CFormDetailsPopupComponent,
    CFormDetailsDeletePopupComponent,
    CFormDetailsDeleteDialogComponent,
    cFormDetailsRoute,
    cFormDetailsPopupRoute,
    CFormDetailsResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...cFormDetailsRoute,
    ...cFormDetailsPopupRoute,
];

@NgModule({
    imports: [
        SamplePmsSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CFormDetailsComponent,
        CFormDetailsDetailComponent,
        CFormDetailsDialogComponent,
        CFormDetailsDeleteDialogComponent,
        CFormDetailsPopupComponent,
        CFormDetailsDeletePopupComponent,
    ],
    entryComponents: [
        CFormDetailsComponent,
        CFormDetailsDialogComponent,
        CFormDetailsPopupComponent,
        CFormDetailsDeleteDialogComponent,
        CFormDetailsDeletePopupComponent,
    ],
    providers: [
        CFormDetailsService,
        CFormDetailsPopupService,
        CFormDetailsResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SamplePmsCFormDetailsModule {}
