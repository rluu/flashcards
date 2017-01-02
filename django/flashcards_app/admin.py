from django.contrib import admin

from .models import FlashcardList
from .models import Flashcard

class FlashcardListAdmin(admin.ModelAdmin):
    list_display = \
        ('name',
         'description',
         'created_by_user',
         'created_timestamp',
         'modified_timestamp',
        )
    
    fields = \
        ('name',
         'description',
        )

    def save_model(self, request, obj, form, change): 
        obj.created_by_user = request.user
        obj.save()

    def save_formset(self, request, form, formset, change): 
        if formset.model == Flashcard:
            instances = formset.save(commit=False)
            for instance in instances:
                instance.created_by_user = request.user
                instance.save()
        else:
            formset.save()

class FlashcardAdmin(admin.ModelAdmin):
    list_display = \
        ('text_side_1',
         'text_side_2',
         'user_notes',
         'created_by_user',
         'created_timestamp',
         'modified_timestamp',
        )
    
    fields = \
        ('text_side_1',
         'text_side_2',
         'user_notes',
        )

    def save_model(self, request, obj, form, change): 
        obj.created_by_user = request.user
        obj.save()


admin.site.register(FlashcardList, FlashcardListAdmin)
admin.site.register(Flashcard, FlashcardAdmin)
