package com.uzelac.service.impl;

import com.uzelac.mapper.DozerConverter;
import com.uzelac.model.db.Server;
import com.uzelac.model.domain.ServerViewModel;
import com.uzelac.model.exception.ValidationException;
import com.uzelac.model.exception.XMLException;
import com.uzelac.model.xml.ServerSpecs;
import com.uzelac.parser.XMLParser;
import com.uzelac.repository.ServerRepository;
import com.uzelac.service.ServerService;
import com.uzelac.validator.ParametersValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ServerServiceImpl implements ServerService
{
    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private XMLParser xmlParser;

    @Autowired
    private DozerConverter dozerConverter;

    @Override
    @Transactional(readOnly = false)
    public ServerViewModel addServer(String xmlSourcePath) throws XMLException, ValidationException
    {
        ServerSpecs serverSpecs = xmlParser.getServerSpecsFromXML(xmlSourcePath);

        ParametersValidator.serverValid(serverSpecs.getId(), serverSpecs.getName());

        Server server = new Server(serverSpecs.getId(), serverSpecs.getName());

        Server addedServer = serverRepository.save(server);
        return dozerConverter.convert(addedServer, ServerViewModel.class);
    }

    @Override
    @Transactional(readOnly = false)
    public ServerViewModel editServer(String serverId, String serverName) throws ValidationException
    {
        validateIfServerExists(serverId);

        Server server = new Server(serverId, serverName);

        Server editedServer = serverRepository.save(server);
        return dozerConverter.convert(editedServer, ServerViewModel.class);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteServer(String serverId) throws ValidationException
    {
        validateIfServerExists(serverId);

        serverRepository.delete(Integer.parseInt(serverId));
    }

    @Override
    public long countServers()
    {
        return serverRepository.count();
    }

    @Override
    public List<ServerViewModel> listServers()
    {
        Iterable<Server> serversFromDB = serverRepository.findAll();

        return dozerConverter.convertAll(serversFromDB, ServerViewModel.class);
    }

    private void validateIfServerExists(String serverId) throws ValidationException
    {
        if (!serverRepository.exists(Integer.parseInt(serverId)))
        {
            throw new ValidationException("Server with id " + serverId + " does not exist!");
        }
    }
}
