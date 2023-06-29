package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.dto.comment.CommentEvent;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.dto.event.ViewsForMapper;
import ru.practicum.model.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event mapFromShortToModel(EventShortDto eventShortDto);

    EventShortDto mapToShortDto(Event event);

    @Mapping(target = "comments", source = "commentEvent.commentCount")
    EventShortDto mapEoShortDtoWithCounComments(Event event, CommentEvent commentEvent);

    Event mapFromNewToModel(NewEventDto newEventDto);

    @Mapping(target = "views", source = "integer")
    @Mapping(target = "confirmedRequests", source = "confRequests")
    EventFullDto mapFromModelToFullDtoWhenCreate(Event event, int confRequests, int integer);

    EventFullDto mapFromModelToFullDto(Event event);

    @Mapping(target = "views", source = "viewsForMapper.viewsForMapper")
    @Mapping(source = "viewsForMapper.confirmedRequestsForMapper", target = "confirmedRequests")
    EventFullDto mapFromModelToFullDtoQ(Event event, ViewsForMapper viewsForMapper);

    List<EventFullDto> mapFromModelListToFullDtoList(List<Event> eventList);

    List<EventShortDto> mapFromModelListToShortDtoList(List<Event> eventList);
}
